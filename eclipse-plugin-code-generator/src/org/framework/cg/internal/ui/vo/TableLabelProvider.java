package org.framework.cg.internal.ui.vo;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableLabelProvider extends LabelProvider implements
		ITableLabelProvider {
	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof FieldModel) {
			FieldModel fm = (FieldModel) element;
			if (columnIndex == 0) {
				return fm.getNo().toString();
			} else if (columnIndex == 1) {
				return fm.getFieldName();
			} else if (columnIndex == 2) {
				return fm.getLabel();
			} else if (columnIndex == 3) {
				return fm.getFieldType();
			} else if (columnIndex == 4) {
				return fm.getTagType();
			} else if (columnIndex == 5) {
				return fm.getNullable().toString();
			} else if (columnIndex == 6) {
				return fm.getQueryable().toString();
			}
		}
		return null;
	}

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}
}