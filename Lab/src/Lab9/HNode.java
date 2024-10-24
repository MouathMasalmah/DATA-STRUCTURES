package Lab9;

public class HNode <T extends Comparable<T>>{
    private  T data;
    private char flag;

    HNode(T data){
        this.data = data;
        this.flag = 'E';
    }

    public void traverse(){
        System.out.println(data);
    }

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data = data;
    }

    public char getFlag(){
        return flag;
    }

    public void setFlag(char flag){
        this.flag = flag;
    }

}
