package data102.filmarkiv.impl;

public class LinearNode<T> {
    public T data;
    public LinearNode<T> neste;

    public LinearNode(T data, LinearNode<T> neste){
        this.data = data;
        this.neste = neste;
    }
}
