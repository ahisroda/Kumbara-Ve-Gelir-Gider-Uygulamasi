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
import com.turan.kumbaravegelirgider.domain.AktifKullaniciDomain;
import com.turan.kumbaravegelirgider.domain.HedefTanimlaDomain;
import com.turan.kumbaravegelirgider.utils.Utils;

public class HedefTanimlaGui extends JDialog {

	public HedefTanimlaGui() {
		initPencere();
	}

	private void initPencere() {
		add(initPanel());
		setSize(500, 190);
		setTitle("Hedef Tanýmla");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
	}

	private JPanel initPanel() {

		JPanel anaPanel = new JPanel(new BorderLayout());
		JPanel solPanel = new JPanel(new GridLayout(3, 2, 5, 5));

		AktifKullaniciDomain aktifKullanici = AktifKullaniciDao.aktifKullaniciIdGetir();
		HedefTanimlaDao.hedefAciklamaGuncelle("Tamamlandý", aktifKullanici.getKullaniciId());

		JLabel adLabel = new JLabel("Hedef Adý");
		JTextField adField = new JTextField(13);

		JLabel paraLabel = new JLabel("Gereken Para");
		JTextField paraField = new JTextField(13);

		ImageIcon ekleIcon = new ImageIcon("icons/ekle_24_Ikon.png");
		JButton ekleButon = new JButton("Ekle", ekleIcon);
		getRootPane().setDefaultButton(ekleButon);

		ImageIcon silIcon = new ImageIcon("icons/sil_24_Ikon.png");
		JButton silButon = new JButton("Sil", silIcon);

		solPanel.add(adLabel);
		solPanel.add(adField);
		solPanel.add(paraLabel);
		solPanel.add(paraField);
		solPanel.add(ekleButon);
		solPanel.add(silButon);

		JList hedeflerList = new JList();
		listele(hedeflerList);
		JScrollPane hedeflerPane = new JScrollPane(hedeflerList);
		hedeflerPane.setBorder(BorderFactory.createTitledBorder("Tanýmlý Hedefler"));
		hedeflerPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		anaPanel.add(hedeflerPane);
		anaPanel.add(solPanel, BorderLayout.WEST);

		hedeflerList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				HedefTanimlaDomain secilenHedef = (HedefTanimlaDomain) hedeflerList.getSelectedValue();

				if (secilenHedef != null) {
					adField.setText(secilenHedef.getAdi());
					paraField.setText(String.valueOf(secilenHedef.getMiktar()));
				}
			}
		});

		ekleButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AktifKullaniciDomain gelenAktifKullaniciId = AktifKullaniciDao.aktifKullaniciIdGetir();
				HedefTanimlaDomain eklenecekHedef = new HedefTanimlaDomain();

				if (!adField.getText().equals("") && !paraField.getText().equals("")) {
					eklenecekHedef.setAdi(adField.getText());
					eklenecekHedef.setMiktar(Integer.parseInt(paraField.getText()));
					eklenecekHedef.setKullaniciId(gelenAktifKullaniciId.getKullaniciId());
					eklenecekHedef.setDurum(1);
					eklenecekHedef.setAciklama("Devam E.");
					HedefTanimlaDao.ekle(eklenecekHedef);
					Utils.alanTemizle(new JTextField[] { adField, paraField });
					listele(hedeflerList);
				} else {
					JOptionPane.showMessageDialog(HedefTanimlaGui.this, "Boþ alan býrakmayýnýz");
				}

			}
		});

		silButon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HedefTanimlaDomain silinecekHedef = (HedefTanimlaDomain) hedeflerList.getSelectedValue();

				if (silinecekHedef != null) {
					HedefTanimlaDao.sil(silinecekHedef);
					listele(hedeflerList);
					Utils.alanTemizle(new JTextField[] { adField, paraField });
				} else {
					JOptionPane.showMessageDialog(HedefTanimlaGui.this, "Lütfen seçim yapýnýz");
				}
			}
		});

		return anaPanel;
	}

	private void listele(JList l) {
		AktifKullaniciDomain gelenAktifKullaniciId = AktifKullaniciDao.aktifKullaniciIdGetir();
		l.setListData(HedefTanimlaDao.listele(gelenAktifKullaniciId.getKullaniciId()).toArray());

	}

}
