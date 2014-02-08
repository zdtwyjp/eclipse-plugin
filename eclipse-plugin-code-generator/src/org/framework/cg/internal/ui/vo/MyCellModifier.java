package org.framework.cg.internal.ui.vo;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;
import org.framework.cg.internal.ui.utils.Constants;

public class MyCellModifier implements ICellModifier {
	private TableViewer tv;
	
	public static String[] TAGS = new String[]{
		Constants.TAG_NAME_INPUT,
		Constants.TAG_NAME_TEXTAREA,
		Constants.TAG_NAME_DATEPICKER,
		Constants.TAG_NAME_MULTISELECT,
		Constants.TAG_NAME_MULTISELECT_11,
		Constants.TAG_NAME_SELECT
	};

	public MyCellModifier(TableViewer tv) {
		this.tv = tv;
	}

	public boolean canModify(Object element, String property) {
		return true;
	}

	@Override
	public Object getValue(Object element, String property) {
		FieldModel fm = (FieldModel) element;
		if (property.equals(Constants.COLUMN_NAME_LABEL)) {
			return fm.getLabel();
		} else if (property.equals(Constants.COLUMN_NAME_TAG_TYPE)) {
			return new Integer(getTagTypeIndex(fm.getTagType()));
		} else if (property.equals(Constants.COLUMN_NAME_NULLABLE)) {
			return new Boolean(fm.getNullable().equals(true));
		} else if (property.equals(Constants.COLUMN_NAME_QUERYABLE)) {
			return new Boolean(fm.getQueryable().equals(true));
		} else if (property.equals(Constants.COLUMN_NAME_COLUMNDISPLAYABLE)) {
			return new Boolean(fm.getColumnDisplayable().equals(true));
		} else if (property.equals(Constants.COLUMN_NAME_UNIQUE)) {
			return new Boolean(fm.getUnique().equals(true));
		}
		throw new RuntimeException("error column name : " + property);
	}

	private int getTagTypeIndex(String tagType) {
		for (int i = 0; i < TAGS.length; i++) {
			if (TAGS[i].equals(tagType)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void modify(Object element, String property, Object value) {
		TableItem item = (TableItem) element;
		FieldModel fm = (FieldModel) item.getData();
		if (property.equals(Constants.COLUMN_NAME_LABEL)) {
			String newValue = (String) value;
			if (newValue.equals("")) {
				return;
			}
			fm.setLabel(newValue);
		} else if (property.equals(Constants.COLUMN_NAME_TAG_TYPE)) {
			Integer comboIndex = (Integer) value;
			if (comboIndex.intValue() == -1) {
				return;
			}
			String newName = TAGS[comboIndex.intValue()];
			fm.setTagType(newName);
		} else if (property.equals(Constants.COLUMN_NAME_NULLABLE)) {
			Boolean newValue = (Boolean) value;
			if (newValue.booleanValue()) {
				fm.setNullable(true);
			} else {
				fm.setNullable(false);
			}
		} else if (property.equals(Constants.COLUMN_NAME_QUERYABLE)) {
			Boolean newValue = (Boolean) value;
			if (newValue.booleanValue()) {
				fm.setQueryable(true);
			} else {
				fm.setQueryable(false);
			}
		} else if (property.equals(Constants.COLUMN_NAME_COLUMNDISPLAYABLE)) {
			Boolean newValue = (Boolean) value;
			if (newValue.booleanValue()) {
				fm.setColumnDisplayable(true);
			} else {
				fm.setColumnDisplayable(false);
			}
		} else if (property.equals(Constants.COLUMN_NAME_UNIQUE)) {
			Boolean newValue = (Boolean) value;
			if (newValue.booleanValue()) {
				fm.setUnique(true);
			} else {
				fm.setUnique(false);
			}
		} else {
			throw new RuntimeException("错误列名:" + property);
		}
		tv.update(fm, null);
	}

}