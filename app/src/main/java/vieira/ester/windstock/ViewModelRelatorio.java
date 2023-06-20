package vieira.ester.windstock;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ViewModelRelatorio extends ViewModel {
    private FirebaseTransacaoRepository transacaoRepository;
    private MutableLiveData<Integer> entradasEscovaLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> saidasEscovaLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> entradasBateriaLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> saidasBateriaLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> entradasRespiradorLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> saidasRespiradorLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> entradasFusivelLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> saidasFusivelLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> entradasLampadaLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> saidasLampadaLiveData = new MutableLiveData<>();

    public ViewModelRelatorio(){

    }

    public ViewModelRelatorio(Context contexto) {
        transacaoRepository = new FirebaseTransacaoRepository(contexto);
    }

    // ENTRADA E SAÍDA DE ESCOVAS
    public void calcularEntradasEscova(Context contexto){
        transacaoRepository.calcularEntradas(new OnDataListener<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                entradasEscovaLiveData.setValue(integer);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }, contexto.getString(R.string.escova));
    }

    public void calcularSaidasEscova(Context contexto){
        transacaoRepository.calcularSaidas(new OnDataListener<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                saidasEscovaLiveData.setValue(integer);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }, contexto.getString(R.string.escova));
    }

    // ENTRADA E SAÍDA DE BATERIAS
    public void calcularEntradasBateria(Context contexto){
        transacaoRepository.calcularEntradas(new OnDataListener<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                entradasBateriaLiveData.setValue(integer);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }, contexto.getString(R.string.bateria));
    }

    public void calcularSaidasBateria(Context contexto){
        transacaoRepository.calcularSaidas(new OnDataListener<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                saidasBateriaLiveData.setValue(integer);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }, contexto.getString(R.string.bateria));
    }

    // ENTRADA E SAÍDA DE RESPIRADORES
    public void calcularEntradasRespirador(Context contexto){
        transacaoRepository.calcularEntradas(new OnDataListener<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                entradasRespiradorLiveData.setValue(integer);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }, contexto.getString(R.string.respirador));
    }

    public void calcularSaidasRespirador(Context contexto){
        transacaoRepository.calcularSaidas(new OnDataListener<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                saidasRespiradorLiveData.setValue(integer);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }, contexto.getString(R.string.respirador));
    }

    // ENTRADA E SAÍDA DE FUSÍVEIS
    public void calcularEntradasFusivel(Context contexto){
        transacaoRepository.calcularEntradas(new OnDataListener<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                entradasFusivelLiveData.setValue(integer);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }, contexto.getString(R.string.fusivel));
    }

    public void calcularSaidasFusivel(Context contexto){
        transacaoRepository.calcularSaidas(new OnDataListener<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                saidasFusivelLiveData.setValue(integer);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }, contexto.getString(R.string.fusivel));
    }

    // ENTRADA E SAÍDA DE LAMPADAS
    public void calcularEntradasLampada(Context contexto){
        transacaoRepository.calcularEntradas(new OnDataListener<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                entradasLampadaLiveData.setValue(integer);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }, contexto.getString(R.string.lampada));
    }

    public void calcularSaidasLampada(Context contexto){
        transacaoRepository.calcularSaidas(new OnDataListener<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                saidasLampadaLiveData.setValue(integer);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }, contexto.getString(R.string.lampada));
    }

    public MutableLiveData<Integer> getEntradasEscovaLiveData() {
        return entradasEscovaLiveData;
    }

    public MutableLiveData<Integer> getSaidasEscovaLiveData() {
        return saidasEscovaLiveData;
    }

    public MutableLiveData<Integer> getEntradasBateriaLiveData() {
        return entradasBateriaLiveData;
    }

    public MutableLiveData<Integer> getSaidasBateriaLiveData() {
        return saidasBateriaLiveData;
    }

    public MutableLiveData<Integer> getEntradasRespiradorLiveData() {
        return entradasRespiradorLiveData;
    }

    public MutableLiveData<Integer> getSaidasRespiradorLiveData() {
        return saidasRespiradorLiveData;
    }

    public MutableLiveData<Integer> getEntradasFusivelLiveData() {
        return entradasFusivelLiveData;
    }

    public MutableLiveData<Integer> getSaidasFusivelLiveData() {
        return saidasFusivelLiveData;
    }

    public MutableLiveData<Integer> getEntradasLampadaLiveData() {
        return entradasLampadaLiveData;
    }

    public MutableLiveData<Integer> getSaidasLampadaLiveData() {
        return saidasLampadaLiveData;
    }
}
