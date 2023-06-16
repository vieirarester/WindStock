package vieira.ester.windstock;

import androidx.fragment.app.Fragment;


public class BarcodeScannerFragment extends Fragment {

    /**private static final String TAG = BarcodeScannerFragment.class.getSimpleName();
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;

    private FragmentBarcodeScannerBinding barcodeScannerBinding;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        barcodeScannerBinding = FragmentBarcodeScannerBinding.inflate(inflater, container, false);
        return barcodeScannerBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createBarcodeDetector();
        createCameraSource();
    }

    private void createBarcodeDetector() {
        barcodeDetector = new BarcodeDetector.Builder(getActivity())
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        if (!barcodeDetector.isOperational()) {
            Toast.makeText(getActivity(), "Barcode detector initialization failed", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }

    private void createCameraSource() {
        cameraSource = new CameraSource.Builder(getActivity(), barcodeDetector)
                .setAutoFocusEnabled(true)
                .build();

        barcodeScannerBinding.surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    startCamera();
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                stopCamera();
            }
        });
    }

    private void startCamera() {
        try {
            cameraSource.start(barcodeScannerBinding.surfaceView.getHolder());
        } catch (IOException e) {
            Log.e(TAG, "Error starting camera", e);
        }
    }

    private void stopCamera() {
        cameraSource.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopCamera();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera();
            } else {
                Toast.makeText(getActivity(), "Camera permission denied", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        }
    }**/
}