package org.framework.cg.internal.ui.vo;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class Sorter extends ViewerSorter {
		private static final int NO = 1;
		private static final int FIELD_NAME = 2;
		private static final int LABLE = 3;
		private static final int FIELD_TYPE = 4;
		private static final int NULLABLE = 5;
		private static final int TAG_TYPE = 6;
		private static final int QUERYABLE = 7;
		
		public static final Sorter NO_ASC = new Sorter(NO);
		public static final Sorter NO_DESC = new Sorter(-NO);
		public static final Sorter FIELD_NAME_ASC = new Sorter(FIELD_NAME);
		public static final Sorter FIELD_NAME_DESC = new Sorter(-FIELD_NAME);
		public static final Sorter LABLE_ASC = new Sorter(LABLE);
		public static final Sorter LABLE_DESC = new Sorter(-LABLE);
		public static final Sorter FIELD_TYPE_ASC = new Sorter(FIELD_TYPE);
		public static final Sorter FIELD_TYPE_DESC = new Sorter(-FIELD_TYPE);
		public static final Sorter NULLABLE_ASC = new Sorter(NULLABLE);
		public static final Sorter NULLABLE_DESC = new Sorter(-NULLABLE);
		public static final Sorter TAG_TYPE_ASC = new Sorter(TAG_TYPE);
		public static final Sorter TAG_TYPE_DESC = new Sorter(-TAG_TYPE);
		public static final Sorter QUERYABLE_ASC = new Sorter(QUERYABLE);
		public static final Sorter QUERYABLE_DESC = new Sorter(-QUERYABLE);
		
		private int sortType ;
		private Sorter(int sortType){
			this.sortType = sortType;
		}
		
		@Override
		public int compare(Viewer viewer, Object e1, Object e2) {
			FieldModel f1 = (FieldModel)e1;
			FieldModel f2 = (FieldModel)e2;
			switch(sortType){
				case NO:{
					Long l1 = f1.getNo();
					Long l2 = f2.getNo();
					return l1.compareTo(l2);
				}
				case -NO:{
					Long l1 = f1.getNo();
					Long l2 = f2.getNo();
					return l2.compareTo(l1);
				}
				case FIELD_NAME:{
					String s1 = f1.getFieldName();
					String s2 = f2.getFieldName();
					return s1.compareTo(s2);
				}
				case -FIELD_NAME:{
					String s1 = f1.getFieldName();
					String s2 = f2.getFieldName();
					return s2.compareTo(s1);
				}
				case LABLE:{
					String s1 = f1.getLabel();
					String s2 = f2.getLabel();
					return s1.compareTo(s2);
				}
				case -LABLE:{
					String s1 = f1.getLabel();
					String s2 = f2.getLabel();
					return s2.compareTo(s1);
				}
				case FIELD_TYPE:{
					String i1 = f1.getFieldType();
					String i2 = f2.getFieldType();
					return i1.compareTo(i2);
				}
				case -FIELD_TYPE:{
					String i1 = f1.getFieldType();
					String i2 = f2.getFieldType();
					return i2.compareTo(i1);
				}
				case NULLABLE:{
					Boolean d1 = f1.getNullable();
					Boolean d2 = f2.getNullable();
					return d1.compareTo(d2);
				}
				case -NULLABLE:{
					Boolean d1 = f1.getNullable();
					Boolean d2 = f2.getNullable();
					return d2.compareTo(d1);
				}
				case TAG_TYPE:{
					String t1 = f1.getTagType();
					String t2 = f2.getTagType();
					return t1.compareTo(t2);
				}
				case -TAG_TYPE:{
					String t1 = f1.getTagType();
					String t2 = f2.getTagType();
					return t2.compareTo(t1);
				}
				case QUERYABLE:{
					Boolean b1 = f1.getQueryable();
					Boolean b2 = f2.getQueryable();
					return b1.compareTo(b2);
				}
				case -QUERYABLE:{
					Boolean b1 = f1.getQueryable();
					Boolean b2 = f2.getQueryable();
					return b2.compareTo(b1);
				}
			}
			return 0;
		}
	}