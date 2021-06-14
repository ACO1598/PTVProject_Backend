package es.loyola.iitv.ptv.DAO;

import java.util.List;

public class Curso {
	private String cursoId, cursoName, userID;
	private List<Grupo> listGrupos;
	
	public Curso() {
		this.setCursoName("");
		this.setCursoName("");
		this.setUserID("");
		this.setListGrupos(null);
	}

	public Curso(String cursoId, String cursoName, String userID, List<Grupo> listGrupos) {
		super();
		this.cursoId = cursoId;
		this.cursoName = cursoName;
		this.userID = userID;
		this.listGrupos = listGrupos;
	}

	public String getCursoId() {
		return cursoId;
	}

	public void setCursoId(String cursoId) {
		this.cursoId = cursoId;
	}

	public String getCursoName() {
		return cursoName;
	}

	public void setCursoName(String cursoName) {
		this.cursoName = cursoName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public List<Grupo> getListGrupos() {
		return listGrupos;
	}

	public void setListGrupos(List<Grupo> listGrupos) {
		this.listGrupos = listGrupos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cursoId == null) ? 0 : cursoId.hashCode());
		result = prime * result + ((cursoName == null) ? 0 : cursoName.hashCode());
		result = prime * result + ((listGrupos == null) ? 0 : listGrupos.hashCode());
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
		if (cursoName == null) {
			if (other.cursoName != null)
				return false;
		} else if (!cursoName.equals(other.cursoName))
			return false;
		if (listGrupos == null) {
			if (other.listGrupos != null)
				return false;
		} else if (!listGrupos.equals(other.listGrupos))
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
		return "Curso [cursoId=" + cursoId + ", cursoName=" + cursoName + ", userID=" + userID + ", listGrupos="
				+ listGrupos + "]";
	}
	
	
}
