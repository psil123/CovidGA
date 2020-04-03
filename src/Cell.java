public class Cell
{
	String rnaseq;
	int removed;
	char type;
	Cell(String a,char t)
	{
		removed=0;
		rnaseq=new String(a);
		type=t;
	}
	public Cell clone()
	{
		Cell temp=new Cell(rnaseq,type);
		temp.removed=this.removed;
		return temp;
	}
	public String toString()
	{
		return type+" "+removed+"\n";
	}
	
}
