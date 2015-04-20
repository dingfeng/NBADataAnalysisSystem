package ui.mainui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class MyTable extends JTable {						// 实现自己的表格类
	private Color selectionColor = new Color(207,228,249);//行选择颜色
    private Color evenRowColor = new Color(183,183,183);//奇数行颜色
    private Color oddRowColor = new Color(225,225,225);//偶数行颜色
    private Color gridColor = new Color(236,233,216);//网格颜色
	// 重写JTable类的构造方法
	public MyTable(DefaultTableModel tableModel) {//Vector rowData, Vector columnNames
		super(tableModel);						// 调用父类的构造方法
		this.setGridColor(gridColor);
		this.setRowHeight(30);
	}
	// 重写JTable类的getTableHeader()方法
	public JTableHeader getTableHeader() {					// 定义表格头
		JTableHeader tableHeader = super.getTableHeader();	// 获得表格头对象
		tableHeader.setReorderingAllowed(false);			// 设置表格列不可重排
		tableHeader.setBackground(Color.black);
		tableHeader.setForeground(Color.white);
		DefaultTableCellRenderer hr = (DefaultTableCellRenderer) tableHeader
				.getDefaultRenderer(); 						// 获得表格头的单元格对象
		hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);		// 设置列名居中显示
		return tableHeader;
	}
	// 重写JTable类的getDefaultRenderer(Class<?> columnClass)方法
	public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {	// 定义单元格
		DefaultTableCellRenderer cr = (DefaultTableCellRenderer) super
				.getDefaultRenderer(columnClass); 						// 获得表格的单元格对象
		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		// 设置单元格内容居中显示
		return cr;
	}
	
	// 重写JTable类的isCellEditable(int row, int column)方法
	public boolean isCellEditable(int row, int column) {				// 表格不可编辑
		return false;
	}
	
	//设置奇偶行的颜色
	public TableCellRenderer getCellRenderer(int row, int column) {
	       return new MyCellRenderer();
	    }
	   
	class MyCellRenderer extends DefaultTableCellRenderer{
	      
	        public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
	        Component cell = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);  
	        this.setColor(cell, table, isSelected, hasFocus, row, column);
	        this.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
	        return cell;
	        }
	        /*
	         * 设置颜色
	         */
	        private void setColor(Component component,JTable table,boolean isSelected,boolean hasFocus,int row,int column){
	        if(isSelected){
	            component.setBackground(selectionColor);
	            setBorder(null);//去掉边
	        }else{
	            if(row%2 == 0){
	               component.setBackground(evenRowColor);  
	            }else{
	               component.setBackground(oddRowColor);
	            }
	        }
	        }
	    }
}
