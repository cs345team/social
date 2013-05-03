/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;  
import java.util.Collections;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import model.Wall;
import org.primefaces.model.LazyDataModel;  
import org.primefaces.model.SortOrder;  

/**
 *
 * @author jichao
 */
public class LazyWallDataModel extends LazyDataModel<Wall>{
    
    private List<Wall> datasource;
    
       
    public LazyWallDataModel(List<Wall> datasource) {  
        this.datasource = datasource;  
    }  
      
    @Override  
    public Wall getRowData(String rowKey) {  
        for(Wall wall : datasource) {  
            if(wall.getId().toString().equals(rowKey))  
                return wall;  
        }  
  
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Wall wall) {  
        return wall.getId().toString();  
    }  
  
    @Override  
    public List<Wall> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {  
        List<Wall> data = new ArrayList<Wall>();  
  
        //filter  
        for(Wall wall : datasource) {  
            boolean match = true;  
  
            for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {  
                try {  
                    String filterProperty = it.next();  
                    String filterValue = filters.get(filterProperty);  
                    String fieldValue = String.valueOf(wall.getClass().getField(filterProperty).get(wall));  
  
                    if(filterValue == null || fieldValue.startsWith(filterValue)) {  
                        match = true;  
                    }  
                    else {  
                        match = false;  
                        break;  
                    }  
                } catch(Exception e) {  
                    match = false;  
                }   
            }  
  
            if(match) {  
                data.add(wall);  
            }  
        }  
  
        //sort  
        if(sortField != null) {  
            Collections.reverse(data);
        }  
  
        //rowCount  
        int dataSize = data.size();  
        this.setRowCount(dataSize);  
  
        //paginate  
        if(dataSize > pageSize) {  
            try {  
                return data.subList(first, first + pageSize);  
            }  
            catch(IndexOutOfBoundsException e) {  
                return data.subList(first, first + (dataSize % pageSize));  
            }  
        }  
        else {  
            return data;  
        }  
    }  
}
