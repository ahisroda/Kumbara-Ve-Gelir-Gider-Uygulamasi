package com.turan.kumbaravegelirgider.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.turan.kumbaravegelirgider.dao.AktifKullaniciDao;
import com.turan.kumbaravegelirgider.dao.GelirGiderDao;
import com.turan.kumbaravegelirgider.dao.HedefTanimlaDao;
import com.turan.kumbaravegelirgider.dao.KumbaraDao;
import com.turan.kumbaravegelirgider.dao.KumbaraTanimlaDao;
import com.turan.kumbaravegelirgider.domain.AktifKullaniciDomain;
import com.turan.kumbaravegelirgider.domain.GelirGiderDomain;
import com.turan.kumbaravegelirgider.domain.HedefTanimlaDomain;
import com.turan.kumbaravegelirgider.domain.KumbaraDomain;
import com.turan.kumbaravegelirgider.domain.KumbaraTanimlaDomain;

public class KumbaraGui extends JDialog {

	public KumbaraGui() {
		initPencere();
	}

	private void initPencere() {
		add(initPanel());
		setSize(700, 200);
		setTitle("Kumbaram");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
	}

	private JPanel initPanel() {

		// Hedef tanýmla para odaklý olsun

		JPanel anaPanel = new JPanel(new BorderLayout());

		JPanel solPanel = new JPanel(new GridLayout(5, 2, 5, 5));

		JLabel kumbaraLabel = new JLabel("Kumbara Adý");
		AktifKullaniciDomain gelenAktifKullanici = AktifKullaniciDao.aktifKullaniciIdGetir();
		JComboBox kumbaraBox = new JComboBox(KumbaraTanimlaDao.listele(gelenAktifKullanici.getKullaniciId()).toArray());

		JLabel miktarLabel = new JLabel("Miktar");
		JTextField miktarField = new JTextField(18);

		JLabel kasadanCekilsinMiaLabel = new JLabel("Kasadan Çekilsin mi?");
		JCheckBox kasadanCekilsinMiCheck = new JCheckBox();
		kasadanCekilsinMiCheck.setSelected(false);

		JLabel b = new JLabel("Kumbaradaki Para: ");
		JLabel kumbaraToplamMiktar = new JLabel();
		b.setVisible(false);
		kumbaraToplamMiktar.setVisible(false);

		ImageIcon ekleIcon = new ImageIcon("icons/ekle_24_Ikon.png");
		JButton ekleButon = new JButton("Ekle", ekleIcon);

		ImageIcon gosterIcon = new ImageIcon("icons/kutu24.png");
		JButton gosterButon = new JButton("Birikim", gosterIcon);

		solPanel.add(kumbaraLabel);
		solPanel.add(kumbaraBox);
		solPanel.add(miktarLabel);
		solPanel.add(miktarField);
		solPanel.add(kasadanCekilsinMiaLabel);
		solPanel.add(kasadanCekilsinMiCheck);
		solPanel.add(b);
		solPanel.add(kumbaraToplamMiktar);
		solPanel.add(ekleButon);
		solPanel.add(gosterButon);

		JList kumbaraList = new JList();
		JScrollPane kumbaraPane = new JScrollPane(kumbaraList);
		kumbaraPane.setBorder(BorderFactory.createTitledBorder("Tanýmlý Hedefler"));
		kumbaraPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		anaPanel.add(kumbaraPane);
		anaPanel.add(solPanel, BorderLayout.WEST);

		ekleButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AktifKullaniciDomain gelenAktifKullanici = AktifKullaniciDao.aktifKullaniciIdGetir();
				KumbaraTanimlaDomain gelenKumbaraId = (KumbaraTanimlaDomain) kumbaraBox.getSelectedItem();
				HedefTanimlaDomain gelenHedefBilgileri = HedefTanimlaDao
						.hedefBilgiGetir(gelenAktifKullanici.getKullaniciId());
				GelirGiderDomain eklenecekGider = new GelirGiderDomain();

				KumbaraDomain eklenecekPara = new KumbaraDomain();

				int kasadakiPara = GelirGiderDao.toplamKasa(gelenAktifKullanici.getKullaniciId());

				Date tarih = new Date();
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				String simdikiTarih = format.format(tarih);

				int miktar = Integer.parseInt(miktarField.getText());
				int hedefMiktar = HedefTanimlaDao.hedefMiktarGetir(gelenKumbaraId.getHedefId(),
						gelenAktifKullanici.getKullaniciId());
				int kumbaraMiktar = KumbaraDao.kumbaraToplamMiktar(gelenAktifKullanici.getKullaniciId(),
						gelenKumbaraId.getId());
				int toplamMiktar = miktar + kumbaraMiktar;

				if (kumbaraBox.getSelectedItem() != null && !miktarField.getText().equals("")) {
					if (kasadanCekilsinMiCheck.isSelected()) {

						if (miktar < kasadakiPara) {
							eklenecekGider.setKullaniciId(gelenAktifKullanici.getKullaniciId());
							eklenecekGider.setMiktar(-miktar);
							eklenecekGider.setTarih(simdikiTarih);
							eklenecekGider.setAciklama(gelenKumbaraId.getAdi() + " kumbarasýna para eklendi");

							GelirGiderDao.ekke(eklenecekGider);

							eklenecekPara.setAciklama(simdikiTarih + " tarihinde " + miktar + " TL eklendi");
							eklenecekPara.setKullaniciId(gelenAktifKullanici.getKullaniciId());
							eklenecekPara.setKumbaraId(gelenKumbaraId.getId());
							eklenecekPara.setMiktar(miktar);
							KumbaraDao.ekle(eklenecekPara);
							
						} else {
							JOptionPane.showMessageDialog(KumbaraGui.this, "Kasada o kadar para yoktur");
						}

					} else {

						if (toplamMiktar >= hedefMiktar && gelenHedefBilgileri.getDurum() == 1) {

							HedefTanimlaDao.hedefDurumGuncelle(gelenKumbaraId.getHedefId(),
									gelenAktifKullanici.getKullaniciId());
							eklenecekPara.setAciklama(simdikiTarih + " tarihinde " + miktar + " TL eklendi ve "
									+ gelenHedefBilgileri.getAdi() + " hedefine ulaþýldý");
							JOptionPane.showMessageDialog(KumbaraGui.this, "Tebrikler, hedefinize ulaþtýnýz...");
							eklenecekPara.setMiktar(miktar);
							eklenecekPara.setKullaniciId(gelenAktifKullanici.getKullaniciId());
							eklenecekPara.setKumbaraId(gelenKumbaraId.getId());
						} else {
							eklenecekPara.setAciklama(simdikiTarih + " tarihinde " + miktar + " TL eklendi");
							eklenecekPara.setKullaniciId(gelenAktifKullanici.getKullaniciId());
							eklenecekPara.setKumbaraId(gelenKumbaraId.getId());
							eklenecekPara.setMiktar(miktar);

						}
						KumbaraDao.ekle(eklenecekPara);

					}
				} else {
					JOptionPane.showMessageDialog(KumbaraGui.this, "Lütfen girdileri tamamlayýnýz");
				}
				kumbaraList.setListData(
						KumbaraDao.listele(gelenAktifKullanici.getKullaniciId(), gelenKumbaraId.getId()).toArray());
			}
		});

		gosterButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				b.setVisible(true);
				kumbaraToplamMiktar.setVisible(true);

				AktifKullaniciDomain gelenAktifKullanici = AktifKullaniciDao.aktifKullaniciIdGetir();
				KumbaraTanimlaDomain gelenKumbaraId = (KumbaraTanimlaDomain) kumbaraBox.getSelectedItem();
				int toplamPara = KumbaraDao.kumbaraToplamMiktar(gelenAktifKullanici.getKullaniciId(),
						gelenKumbaraId.getId());

				kumbaraToplamMiktar.setText(String.valueOf(toplamPara) + " TL");
				kumbaraList.setListData(
						KumbaraDao.listele(gelenAktifKullanici.getKullaniciId(), gelenKumbaraId.getId()).toArray());

			}
		});

		return anaPanel;
	}

}
