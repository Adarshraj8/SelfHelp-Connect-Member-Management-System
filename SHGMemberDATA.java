package max.reg;

import java.util.List;

public class SHGMemberDATA {
private List<ShgDTO> shgllist ;
private List<MemberDTO> memlist;

ShgDTO shgDTO;


public ShgDTO getShgDTO() {
	return shgDTO;
}
public void setShgDTO(ShgDTO shgDTO) {
	this.shgDTO = shgDTO;
}
public List<ShgDTO> getShgllist() {
	return shgllist;
}
public void setShgllist(List<ShgDTO> shgllist) {
	this.shgllist = shgllist;
}
public List<MemberDTO> getMemlist() {
	return memlist;
}
public void setMemlist(List<MemberDTO> memlist) {
	this.memlist = memlist;
}



}
