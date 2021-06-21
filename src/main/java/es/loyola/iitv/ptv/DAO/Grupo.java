package es.loyola.iitv.ptv.DAO;

import org.bson.Document;

public class Grupo {
	private String groupName, groupDescription;
	
	public Grupo() {
		this.setGroupName("");
		this.setGroupDescription("");
	}
	
	public Grupo(String groupName, String groupDescription) {
		super();
		this.groupName = groupName;
		this.groupDescription = groupDescription;
	}

	public Grupo(Document docGroup) {
		
		if(docGroup != null) {
			if(docGroup.containsKey("GroupName")) {
				this.setGroupName(docGroup.getString("GroupName")) ;
			}
			if(docGroup.containsKey("GroupDescription")) {
				this.setGroupName(docGroup.getString("GroupDescription"));
			}
		}else {
			throw new NullPointerException("Error al conseguir el documento");
		}
		
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupDescription == null) ? 0 : groupDescription.hashCode());
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
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
		Grupo other = (Grupo) obj;
		if (groupDescription == null) {
			if (other.groupDescription != null)
				return false;
		} else if (!groupDescription.equals(other.groupDescription))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Grupo [groupName=" + groupName + ", groupDescription=" + groupDescription + "]";
	}
	
	
}
