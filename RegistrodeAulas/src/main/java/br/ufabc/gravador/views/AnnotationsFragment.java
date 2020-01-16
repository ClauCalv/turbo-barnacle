package br.ufabc.gravador.views;

import br.ufabc.gravador.*;
import br.ufabc.gravador.models.Gravacao;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class AnnotationsFragment extends Fragment {

    protected Gravacao gravacao;
    private int selectedID = -1;

    private Spinner annotationsSelector;
    private EditText annotationContent, annotationName;
    private ImageButton annotationNewButton, annotationTakePicture;
    private Button annotationSave;
    private TextView annotationTime;
    private BaseAdapter adapter;
    private AnnotationFragmentListener activityListener;
    private boolean hasTextChanged = false, textEmpty = true, ignoreOnce = true;

    public AnnotationsFragment () {
        // Required empty public constructor
    }

    public static AnnotationsFragment getInstance(){
        AnnotationsFragment fragment = new AnnotationsFragment();
        return fragment;
    }

    @Override
    public void onAttach ( Context context ) {
        super.onAttach(context);
        if ( context instanceof AnnotationFragmentListener ) {
            activityListener = (AnnotationFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach () {
        super.onDetach();
        activityListener = null;
    }

    public interface AnnotationFragmentListener {
        Gravacao getGravacao();
        long getGravacaoTime();
        void receiveFragment(AnnotationsFragment f);
        void alertSaveReturn();
    }

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        //TODO?
    }

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_annotations, container, false);
    }

    @Override
    public void onActivityCreated ( @Nullable Bundle savedInstanceState ) {
        super.onActivityCreated(savedInstanceState);
        View master = getView();

        annotationsSelector = master.findViewById(R.id.annotationsSelector);
        adapter = new BaseAdapter(){
            @Override
            public int getCount () {return gravacao==null ? 1 : gravacao.hasAnnotation() ? gravacao.getAnnotationCount() : 1; }
            @Override
            public Object getItem ( int i ) { return gravacao==null ? null : gravacao.getAnnotationOnPos(i); }
            @Override
            public long getItemId ( int i ) { return gravacao==null ? -1 : gravacao.getAnnotationIDOnPos(i); }

            class ViewHolder{ private TextView txtview; }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if (convertView == null) {
                    convertView = LayoutInflater.from(getActivity()).inflate(R.layout.spinner_item, null);
                    holder = new ViewHolder();
                    holder.txtview = convertView.findViewById(R.id.spinner_textitem);
                    convertView.setTag(holder);
                } else
                    holder = (ViewHolder) convertView.getTag();

                holder.txtview.setText(gravacao == null || !gravacao.hasAnnotation()
                        ? "CRIE UMA ANOTAÇÃO"
                        : gravacao.getAnnotationOnPos(position).getName());
                return convertView;
            }

            @Override
            public View getDropDownView ( int position, View convertView, ViewGroup parent ) {
                return getView(position, convertView, parent);
            }
        };
        annotationsSelector.setAdapter(adapter);
        annotationsSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected ( AdapterView<?> adapterView, View view, int i, long l ) {
                if(ignoreOnce) ignoreOnce = false;
                else selectNewAnnotation(l);
            }
            @Override
            public void onNothingSelected ( AdapterView<?> adapterView ) { }
        });

        annotationContent = master.findViewById(R.id.annotationContent);
        annotationContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) { }
            @Override
            public void onTextChanged ( CharSequence charSequence, int i, int i1, int i2 ) {
                if(i1!=0 || i2!=0){
                    hasTextChanged = true;
                }
            }
            @Override
            public void afterTextChanged ( Editable editable ) {}
        });
        annotationContent.setEnabled(false);

        annotationName = master.findViewById(R.id.annotationName);

        annotationNewButton = master.findViewById(R.id.annotationNewButton);
        annotationNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) {
                newButtonOnClick();
            }
        });

        annotationTakePicture = master.findViewById(R.id.annotationTakePicture);
        annotationTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) { takePictureOnClick();
            }
        });

        annotationSave = master.findViewById(R.id.annotationSave);
        annotationSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View view ) { saveOnClick();
            }
        });

        annotationTime = master.findViewById(R.id.annotationTime);

        gravacao = activityListener.getGravacao();
        activityListener.receiveFragment(this);
        loadNewGravacao();
    }

    public void loadNewGravacao(){
        annotationName.setText("Crie uma anotação");
        annotationTime.setText("00:00");
    }

    public void alertSave(final boolean alertActivity){
        if(hasTextChanged && selectedID != -1)
            new AlertDialog.Builder(getContext())
                    .setMessage("Salvar mudanças na anotação anterior?")
                    .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(i == AlertDialog.BUTTON_POSITIVE)
                                saveOnClick();
                            alertSaveReturn(alertActivity);
                        }
                    })
                    .setNegativeButton("Descartar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick ( DialogInterface dialogInterface, int i ) {
                            alertSaveReturn(alertActivity);
                        }
                    })
                    .show();
        else alertSaveReturn(alertActivity);
    }

    public void alertSaveReturn(boolean alertActivity){
        if (alertActivity){
            activityListener.alertSaveReturn();
        } else {
            String name = "Anotacão "+(gravacao.getAnnotationCount()+1);
            Gravacao.Annotations a = gravacao.addAnnotation(activityListener.getGravacaoTime(), name);
            selectNewAnnotation(a.id);
            ignoreOnce = true;
            adapter.notifyDataSetChanged();
        }
    }

    public void selectNewAnnotation(long ID){
        if(ID == -1)
            return;
        selectedID = (int) ID;
        Gravacao.Annotations a = gravacao.getAnnotation(selectedID);
        annotationName.setText(a.getName());
        annotationTime.setText(a.getTimeStamp());
        annotationContent.setEnabled(true);
        annotationContent.setText(a.getText());

        if(a.hasImage()) {
            //TODO images
        }
    }

    public void saveOnClick(){
        gravacao.setAnnotationText(selectedID, annotationContent.getText().toString());
        hasTextChanged = false;
    }

    public void newButtonOnClick(){
        alertSave(false);
     }

    public void takePictureOnClick(){
        //TODO
    }
}
