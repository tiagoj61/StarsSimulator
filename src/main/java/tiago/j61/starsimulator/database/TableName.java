package tiago.j61.starsimulator.database;

import java.lang.annotation.Annotation;

import javax.persistence.Table;

import tiago.j61.starsimulator.model.Usuario;

public class TableName {
	public static String getJPAName(Class clazz) {
		Annotation name = clazz.getAnnotation(Table.class);
		if (name != null)
			return ((Table) name).name();
		return clazz.getSimpleName() + " ";
	}

	public static String getJPAInnerJoinName(Class clazz1, Class clazz2) {
		Annotation name1 = clazz1.getAnnotation(Table.class);
		Annotation name2 = clazz2.getAnnotation(Table.class);
		String nameReturn;
		if (name1 != null) {
			nameReturn = ((Table) name1).name();
		} else {
			nameReturn = clazz1.getSimpleName();
		}
		if (name2 != null) {
			nameReturn = nameReturn + "_" + ((Table) name2).name();
		} else {
			nameReturn = nameReturn + "_" + clazz2.getSimpleName();
		}
		return nameReturn + " ";
	}

	public static String getNamedField(Class clazz, int posicao) {
		posicao = posicao - 1;
		String name = "";
		if (clazz.getDeclaredFields().length >= posicao)
			name = clazz.getDeclaredFields()[posicao].getName();
		return name+" ";
	}
}
