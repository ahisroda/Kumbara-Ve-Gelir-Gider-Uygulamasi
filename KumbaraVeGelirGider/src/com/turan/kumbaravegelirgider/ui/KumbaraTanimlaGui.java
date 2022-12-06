package com.turan.kumbaravegelirgider.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.turan.kumbaravegelirgider.dao.AktifKullaniciDao;
import com.turan.kumbaravegelirgider.dao.HedefTanimlaDao;
import com.turan.kumbaravegelirgider.dao.KumbaraTanimlaDao;
import com.turan.kumbaravegelirgider.domain.AktifKullaniciDomain;
import com.turan.kumbaravegelirgider.domain.HedefTanimlaDomain;
import com.turan.kumbaravegelirgider.domain.KumbaraTanimlaDomain;
import com.turan.kumbaravegelirgider.utils.Utils;

public class KumbaraTanimlaGui extends JDialog {

	public KumbaraTanimlaGui() {
		initPencere();
	}

	private void initPencere() {
		add(initPanel());
		setSize(650, 200);
		setTitle("Kumbara Tanýmla");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
	}

	private JPanel initPanel() {

		// Hedef tanýmla para odaklý olsun

		JPanel anaPanel = new JPanel(new BorderLayout());

		JPanel solPanel = new JPanel(new GridLayout(3, 2, 5, 5));

		JLabel adLabel = new JLabel("Kumbara Adý");
		JTextField adField = new JTextField(13);

		JLabel hedefLabel = new JLabel("(Ýsteðe Baðlý) Hedef");
		AktifKullaniciDomain gelenAktifKullanici = AktifKullaniciDao.aktifKullaniciIdGetir();
		JComboBox hedefBox = new JComboBox(
				HedefTanimlaDao.boxdaListele(gelenAktifKullanici.getKullaniciId()).toArray());
		hedefBox.setSelectedItem(null);

		ImageIcon ekleIcon = new ImageIcon("icons/ekle_24_Ikon.png");
		JButton ekleButon = new JButton("Ekle", ekleIcon);
		getRootPane().setDefaultButton(ekleButon);

		ImageIcon silIcon = new ImageIcon("icons/sil_24_Ikon.png");
		JButton silButon = new JButton("Sil", silIcon);

		solPanel.add(adLabel);
		solPanel.add(adField);
		solPanel.add(hedefLabel);
		solPanel.add(hedefBox);
		solPanel.add(ekleButon);
		solPanel.add(silButon);

		JList kumbaralarList = new JList();
		listele(kumbaralarList);
		JScrollPane kumbaralarPane = new JScrollPane(kumbaralarList);
		kumbaralarPane.setBorder(BorderFactory.createTitledBorder("Tanýmlý Kumbaralar"));
		kumbaralarPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		anaPanel.add(kumbaralarPane);
		anaPanel.add(solPanel, BorderLayout.WEST);

		ekleButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AktifKullaniciDomain gelenAktifKullanici = AktifKullaniciDao.aktifKullaniciIdGetir();
				HedefTanimlaDomain gelenHedefId = (HedefTanimlaDomain) hedefBox.getSelectedItem();
				KumbaraTanimlaDomain kumbaraTanimla = new KumbaraTanimlaDomain();

				if (!adField.getText().equals("")) {
					kumbaraTanimla.setAdi(adField.getText());

					if (hedefBox.getSelectedItem() == null) {
						kumbaraTanimla.setHedefId(0);
						kumbaraTanimla.setAciklama(adField.getText());
					} else {
						kumbaraTanimla.setHedefId(gelenHedefId.getId());
						kumbaraTanimla.setAciklama(adField.getText() + " | Hedef: " + gelenHedefId.getAdi());
					}

					kumbaraTanimla.setKullaniciId(gelenAktifKullanici.getKullaniciId());
					KumbaraTanimlaDao.ekle(kumbaraTanimla);
					listele(kumbaralarList);
					hedefBox.setSelectedItem(null);
					adField.setText("");

				} else {
					JOptionPane.showMessageDialog(KumbaraTanimlaGui.this, "Gerekli alaný doldurunuz");
				}

			}
		});

		silButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				KumbaraTanimlaDomain silinecekKumbara = (KumbaraTanimlaDomain) kumbaralarList.getSelectedValue();
				if (silinecekKumbara != null) {
					KumbaraTanimlaDao.sil(silinecekKumbara);
					listele(kumbaralarList);
				} else {
					JOptionPane.showMessageDialog(KumbaraTanimlaGui.this, "Lütfen seçim yapýnýz");
				}
			}
		});

		return anaPanel;
	}

	private void listele(JList l) {
		AktifKullaniciDomain gelenAktifKullanici = AktifKullaniciDao.aktifKullaniciIdGetir();

		l.setListData(KumbaraTanimlaDao.listele(gelenAktifKullanici.getKullaniciId()).toArray());

	}

}
