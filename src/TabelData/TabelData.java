/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TabelData;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Yoffa
 */
public class TabelData extends AbstractTableModel {
    List <DataFilm> data = new ArrayList<>();
    
    @Override
    public int getRowCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return data.size();
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        switch(columnIndex){
        case 0: return data.get(rowIndex).getIdfilm();
        case 1: return data.get(rowIndex).getJudulfilm();
        case 2: return data.get(rowIndex).getGenre();
        case 3: return data.get(rowIndex).getTahun();    
        case 4: return data.get(rowIndex).getSutradara();
        case 5: return data.get(rowIndex).getRumahproduksi();
        case 6: return data.get(rowIndex).getJenis();
        case 7: return data.get(rowIndex).getPlatform();                
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int kolom){
    switch(kolom){
        case 0: return "IDFILM";
        case 1: return "JUDULFILM";
        case 2: return "GENRE";
        case 3: return "TAHUN";
        case 4: return "SUTRADARA";
        case 5: return "RUMAHPRODUKSI";
        case 6: return "JENIS";
        case 7: return "PLATFORM";
        default: return null;
    	}
    }
    
    public void add(DataFilm a){
        data.add(a);
        fireTableRowsInserted(getRowCount(),getColumnCount());
    }

    public void delete (int col,int row){
        data.remove(col);
        fireTableRowsDeleted(col,row);
    }
    
    public DataFilm get(int row){
        return (DataFilm) data.get(row);
    }
    
}
