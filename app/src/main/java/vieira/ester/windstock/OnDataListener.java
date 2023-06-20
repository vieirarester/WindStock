package vieira.ester.windstock;

public interface OnDataListener<T> {
    void onSuccess(T t);
    void onFailure(Exception e);
}
