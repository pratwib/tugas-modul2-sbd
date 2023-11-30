/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import TabelData.DataFilm;
import TabelData.TabelData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


/**
 *
 * @author Yoffa
 */
public class MainForm extends javax.swing.JFrame {

    private String mf_idfilm;
    private String mf_judulfilm;
    private String mf_genre;
    private String mf_tahun;
    private String mf_sutradara;
    private String mf_rumahproduksi;
    private String mf_idjenis;
    private String mf_jenis;
    private String mf_idplatform;    
    private String mf_platform;
    private String output;
    private TabelData tabeldata;
    private dbconnections c;
    private Statement script;
    
    private void Tampil(){
        try {
            int row = DB_FILM.getRowCount();
            for(int i=0;i<row;i++){
                tabeldata.delete(0, row);
            }
            String sql="SELECT *FROM DB_FILM";
            ResultSet rsShow = c.script.executeQuery(sql);
            while(rsShow.next()){
                DataFilm d = new DataFilm();
                d.setIdfilm(rsShow.getString("IDFILM"));
                d.setJudulfilm(rsShow.getString("JUDULFILM"));
                d.setGenre(rsShow.getString("GENRE"));
                d.setTahun(rsShow.getString("TAHUN"));
                d.setSutradara(rsShow.getString("SUTRADARA"));
                d.setRumahproduksi(rsShow.getString("RUMAHPRODUKSI"));
                d.setJenis(rsShow.getString("JENIS"));
                d.setPlatform(rsShow.getString("PLATFORM"));
                tabeldata.add(d);
            }
        }catch(Exception e){
            System.err.print(e);
        }
    } 
    public String showData(DataFilm dm){
        idfilm.setText(dm.getIdfilm());
        judulfilm.setText(dm.getJudulfilm());
        genre.setText(dm.getGenre());
        tahun.setText (dm.getTahun());
        sutradara.setText (dm.getSutradara());
        rumahproduksi.setText (dm.getRumahproduksi());
        idjenis.setText (dm.getIdjenis());
        idplatform.setText (dm.getIdplatform());
        insert.setEnabled(false);
        return dm.getIdfilm();
    }
    private void insertData(){
        mf_idfilm = idfilm.getText();
        mf_judulfilm = judulfilm.getText();
        mf_genre = genre.getText();
        mf_tahun = tahun.getText();
        mf_sutradara = sutradara.getText();
        mf_rumahproduksi = rumahproduksi.getText();
        mf_idjenis = idjenis.getText();
        mf_idplatform = idplatform.getText();
        if(idfilm!=null&&judulfilm!=null&&genre!=null&&tahun!=null&&sutradara!=null&&rumahproduksi!=null&&idjenis!=null&&idplatform!=null){
            try{
                String sql = "INSERT INTO FILM (idfilm, judulfilm, genre, tahun, sutradara, rumahproduksi, idjenis, idplatform) values ('" +mf_idfilm+ "','" +mf_judulfilm+ "','"+mf_genre+"','"+mf_tahun+"','" +mf_sutradara+ "','"+mf_rumahproduksi+"','"+mf_idjenis+"',"+mf_idplatform+")";
                c.script.executeUpdate(sql);
            }catch(SQLException e){
                System.err.print(e);
            }
            Tampil();
            JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
            clearForm();
        }
        else{
            JOptionPane.showMessageDialog(null,"Setiap kolom harus diisi!");
        }
    }
    private void deleteData(){
    int app;
    mf_idfilm = idfilm.getText();
    if ((app=JOptionPane.showConfirmDialog(null,"Yakin ingin hapus data?","Hapus Data", JOptionPane.YES_NO_OPTION))==0){
    try {
        String sqlid = "SELECT IDFILM from FILM where idfilm="+mf_idfilm+"";
        ResultSet rsShow = c.script.executeQuery(sqlid);
        while (rsShow.next()){
	output = rsShow.getString(1);
}
        String sql = "DELETE from FILM where idfilm ="+mf_idfilm+"";
        c.script.executeUpdate(sql);
        Tampil();
        JOptionPane.showMessageDialog (null,"Berhasil dihapus");
        clearForm();
        tampil1();
    }
    catch (SQLException e){
        System.err.print(e);
            }
        }
    }
    private void updateData(String mf_idfilm){
        int app;
                       
        if((app = JOptionPane.showConfirmDialog(null, "Yakin ingin update date?","Ubah Data",JOptionPane.YES_NO_OPTION))==0){
        	try{ //Query untuk update pada table database
                    mf_idfilm = idfilm.getText();
                    mf_judulfilm = judulfilm.getText();
                    mf_genre = genre.getText();
                    mf_tahun = tahun.getText();
                    mf_sutradara = sutradara.getText();
                    mf_rumahproduksi = rumahproduksi.getText();	
                    mf_idjenis = idjenis.getText();	
                    mf_idplatform = idplatform.getText();	
                             
                    String sqlid = "SELECT IDFILM from FILM where idfilm=" +mf_idfilm+ "";
                    ResultSet rsShow = c.script.executeQuery(sqlid);
                    while (rsShow.next()){
                    output = rsShow.getString(1);
                    } 
                        String sql = "UPDATE FILM SET idfilm='"+mf_idfilm+"',judulfilm='"+mf_judulfilm+"',genre='"+mf_genre+"',tahun='"+mf_tahun+"',sutradara='"+mf_sutradara+"',rumahproduksi='"+mf_rumahproduksi+"',idjenis='"+mf_idjenis+"',idplatform="+mf_idplatform+" where idfilm="+mf_idfilm+"" ;
       			c.script.executeUpdate(sql);
        		Tampil();
                        tampil1();
		//menampilkan message dialog bahwa data telah update
        	JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
        		clearForm();
        	}
        	catch(SQLException ex){
        	System.err.print(ex);
        }
    }}
    
    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        c = new dbconnections();
        tabeldata = new TabelData();
        DB_FILM.setModel(tabeldata);
        Tampil();

    }
    public void clearForm(){
        judulfilm.setText(null);
        genre.setText(null);
        tahun.setText(null);
        sutradara.setText (null);
        rumahproduksi.setText (null);
        idjenis.setText (null);
        idplatform.setText (null);
        idfilm.setText(null);
        
    }
    public void tampil1(){
    	insert.setEnabled(true);
    	delete.setEnabled(true);
    	update.setEnabled(true);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        DB_FILM = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        judulfilm = new javax.swing.JTextField();
        genre = new javax.swing.JTextField();
        tahun = new javax.swing.JTextField();
        sutradara = new javax.swing.JTextField();
        rumahproduksi = new javax.swing.JTextField();
        idjenis = new javax.swing.JTextField();
        idplatform = new javax.swing.JTextField();
        idfilm = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        insert = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        DB_FILM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        DB_FILM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DB_FILMMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(DB_FILM);

        jLabel1.setText("Judul Film");

        jLabel2.setText("Genre");

        jLabel3.setText("Tahun");

        jLabel4.setText("Sutradara");

        jLabel5.setText("Rumah Produksi");

        jLabel6.setText("ID Jenis");

        jLabel7.setText("ID Platform");

        jLabel8.setText("ID Film");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(121, 121, 121)
                        .addComponent(idjenis))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idplatform, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(108, 108, 108)
                        .addComponent(sutradara))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(73, 73, 73)
                        .addComponent(rumahproduksi))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idfilm, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(judulfilm, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(genre, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tahun, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idfilm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(judulfilm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sutradara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rumahproduksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idjenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idplatform, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        insert.setText("INSERT");
        insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertActionPerformed(evt);
            }
        });

        update.setText("UPDATE");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delete)
                    .addComponent(update)
                    .addComponent(insert))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(insert)
                .addGap(60, 60, 60)
                .addComponent(update)
                .addGap(60, 60, 60)
                .addComponent(delete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DB_FILMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DB_FILMMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
            showData(this.tabeldata.get(DB_FILM.getSelectedRow()));
            insert.setEnabled(false);
            delete.setEnabled(true);
            update.setEnabled(true);
        }
    }//GEN-LAST:event_DB_FILMMouseClicked

    private void insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertActionPerformed
        // TODO add your handling code here:
        insertData();
    }//GEN-LAST:event_insertActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
        mf_idfilm = idfilm.getText();
        updateData(mf_idfilm);
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        deleteData();
    }//GEN-LAST:event_deleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DB_FILM;
    private javax.swing.JButton delete;
    private javax.swing.JTextField genre;
    private javax.swing.JTextField idfilm;
    private javax.swing.JTextField idjenis;
    private javax.swing.JTextField idplatform;
    private javax.swing.JButton insert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField judulfilm;
    private javax.swing.JTextField rumahproduksi;
    private javax.swing.JTextField sutradara;
    private javax.swing.JTextField tahun;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
