package br.senac.pi.jogodavelha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {
    //quadadomos  a instancia da nossa view
    private View view;
    //CONST. da tag de cada botão.
    // Utilizamos essa const. para recuperar o botão atraveis do metados getQuadrado
    private final String QUADRADO = "quadrado";
    //Const. que vai ser impressa no texto do botão
    private  final String BOLA = "O";
    //Const. que vai ser impressa no texte do botão
    private final String XIS = "X";
    //quadamos o ultimo valor jogado
    private String lastPlay = "X";

    //matriz que define as condições para o jogo acabar
    int[][]estadoFinal = new int[][]{
            //verificamos as linhas
            {1,2,3},
            {4,5,6},
            {7,8,9},
            //verificamos as colunas
            {1,4,7},
            {2,5,8},
            {3,6,9},
            //verificamos as diagonais
            {1,5,9},
            {3,5,7},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //estamos inflando nosso XML e quadando a instacia de VIEW
        setView(getLayoutInflater().inflate(R.layout.activity_main, null));
        //aqui estamos passando a instancia  da nossa VIEW  para a nossa Activity
        setContentView(getView());

    }
    public  void clickQuadrado(View v){
        if (!isFim()) {//verifica se o jogo acabou
            if (((Button) v).getText().equals("")) {
                //verifica se o texto do botão  que esta vindo  pela variavel v
                //é diferente de vazio
                if (lastPlay.equals(XIS)) {//verificando se o ultimo valor jogado ele é migual a XIS
                    ((Button) v).setText(BOLA);//se for igual a XIS jogamos BOLA
                    setLastPlay(BOLA);//setamos lastPlay como BOLA
                    //para que na proxíma vez ele não entre nessa condição
                } else {
                    ((Button) v).setText(XIS);//setamos o texto do botão como X
                    setLastPlay(XIS);//setamos o valor de lastPlay como x
                }
            } else {
                //imprime uma mensagem indicando que já foi jogado nesse campo
                Toast.makeText(getView().getContext(), R.string.ops_selecione, Toast.LENGTH_LONG).show();
            }


            isFim();//aqui verificamos se o fim do jogo
        }
    }

    public Button getQuadrado(int tagNum){
        //retorna o respectivo quadrado requerido pela variavel tagNum
        return (Button)getView().findViewWithTag(QUADRADO + tagNum);

    }

    public void NewGame (View v){
        //Para
        setEnableQuadrado(true);//ativa  os quadrados
        setColorBlack();//pinta os quadrados de preto
        limpaCampos();//invoca o métado que limpa os campos
        RadioButton rX  = (RadioButton)getView().findViewById(R.id.rbX);
        //Retorna a instancia  do nosso radiobutton rbX
        //e armazena  a inst. na variavel rX
        RadioButton rO = (RadioButton )getView().findViewById(R.id.rbO);
        //radiobutton rO
        if (rX.isChecked()){ //verificamos se rX está checado
            setLastPlay(BOLA);//
            //alteramos o lastPlay para o inverso do que o usuario esta querendo jogar
            //fazemos porque sempre jogamos  o oposto que estiver contido em lastPlay
        }else {
            if (rO.isChecked()){
                setLastPlay(XIS);
                //indicamos que o ultimo a jogar  foi o XIS  porque queremos começa a jogar com a BOLA
            }
        }
    }
    public void limpaCampos(){
        for (int i=1; i<=9; ++i ){//percorrer dodos os botões
            if (getQuadrado(i)!= null){//verificar se o botão que está recuperando é diferente de null
                getQuadrado(i).setText("");//setamos o texto como vazio ,limpa o compo
            }
        }

    }

    public void  setEnableQuadrado(boolean b){
        for (int i=1; i<=9; ++i ){//percorremos todos os itens
            if (getQuadrado(i)!=null){//depois verificamos se o quadrado é != de null
                getQuadrado(i).setEnabled(b);//se for,passamos o valor de b para o respectivo quadrado
            }
        }
    }

    public void setColorQuadrados(int btn, int colorX ){
        //recebe o numero do botão pela variavel
        //passa a instancia da nossa  cor pela variavel colorX
        getQuadrado(btn).setTextColor(getResources().getColor(colorX));
        //recuperamos botão e setamos o textColor com a cor passada pela variavel colorX
    }
    public void setColorBlack(){
        for (int i=0; i<=9; ++i){//percorrendo todos os itens
            if (getQuadrado(i)!=null){//verifica se o quadrado é != de null
                setColorQuadrados(i,R.color.black);//enviar a cor preta para o metado setColor quadrados
            }
        }
    }

    public  boolean isFim(){
        //vamos percorrer todas as nossas condicões definidas na nossa matriz
        for (int x=0; x<=7; ++x){

            String s1 = getQuadrado(estadoFinal[x][0]).getText().toString();
            String s2 = getQuadrado(estadoFinal[x][1]).getText().toString();
            String s3 = getQuadrado(estadoFinal[x][2]).getText().toString();

            //verificamos se s1,s2 e s3 são diferentes de vazio
            //temos que verificar  porque  senão o jogo acaba na primeira jogada
            //porque os textos dos botões  vão conter o mesmo texto
            //no caso,serão vazio
            if      ((!s1.equals(""))&&
                    (!s2.equals(""))&&
                    (!s3.equals(""))){
                if (s1.equals(s2)&&(s2.equals(s3))){
                    //caso os botões sejam iguais serão pintados de vermelho
                    setColorQuadrados(estadoFinal[x][0],R.color.red );
                    setColorQuadrados(estadoFinal[x][1],R.color.red );
                    setColorQuadrados(estadoFinal[x][2], R.color.red);
                    //indicamos que o jogo acabou
                    Toast.makeText(getView().getContext(),R.string.fim_de_jogo,Toast.LENGTH_LONG).show();
                    return true;//verdadeiro,ou seja,estamos disendo que o jogo acabou
                }
            }
        }
        return false;
    }
    public View getView(){
        return view;
    }

    public void setView (View view){

        this.view = view;

    }

    public  String getLastPlay(){
        return lastPlay;


    }

    public void setLastPlay(String lastPlay){
        this.lastPlay = lastPlay;

    }
}



