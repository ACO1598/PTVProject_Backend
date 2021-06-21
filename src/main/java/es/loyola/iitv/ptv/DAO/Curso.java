package es.loyola.iitv.ptv.DAO;

import java.util.List;

public class Curso {
	private String cursoId, Curso, userID;
	private List<Grupo> Grupo;
	
	public Curso() {
		this.setCursoName("");
		this.setCursoName("");
		this.setUserID("");
		this.setListGrupos(null);
	}

	public Curso(String cursoId, String cursoName, String userID, List<Grupo> listGrupos) {
		super();
		this.cursoId = cursoId;
		this.Curso = cursoName;
		this.userID = userID;
		this.Grupo = listGrupos;
	}

	public String getCursoId() {
		return cursoId;
	}

	public void setCursoId(String cursoId) {
		this.cursoId = cursoId;
	}

	public String getCursoName() {
		return Curso;
	}

	public void setCursoName(String cursoName) {
		this.Curso = cursoName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public List<Grupo> getListGrupos() {
		return Grupo;
	}

	public void setListGrupos(List<Grupo> listGrupos) {
		this.Grupo = listGrupos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cursoId == null) ? 0 : cursoId.hashCode());
		result = prime * result + ((Curso == null) ? 0 : Curso.hashCode());
		result = prime * result + ((Grupo == null) ? 0 : Grupo.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (cursoId == null) {
			if (other.cursoId != null)
				return false;
		} else if (!cursoId.equals(other.cursoId))
			return false;
		if (Curso == null) {
			if (other.Curso != null)
				return false;
		} else if (!Curso.equals(other.Curso))
			return false;
		if (Grupo == null) {
			if (other.Grupo != null)
				return false;
		} else if (!Grupo.equals(other.Grupo))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Curso [cursoId=" + cursoId + ", cursoName=" + Curso + ", userID=" + userID + ", listGrupos="
				+ Grupo + "]";
	}
	
	
}
