public class ConfigHelper {

    public static final int CONFIG_EXAMPLE_1 = 1, CONFIG_EXAMPLE_2 = 2;

    public static final int CONFIG_EXAMPLE_2_MODE_A = 0, CONFIG_EXAMPLE_2_MODE_B = 0;
    private int configExample1Status, configExample2Status;

    private ConfigHelper(){}

    //gets publicos, sets privados
    public int getConfig(int requestCode){
        switch (requestCode){
            case CONFIG_EXAMPLE_1:
                return configExample1Status;
            case CONFIG_EXAMPLE_2:
                return configExample2Status;
            default:
                return -1;
        }
    }

    private void setConfig(int requestCode, int value){
        //switch ( ... );
    }

    public static ConfigHelper load(){
        StringBuffer a = abrirArquivoExemplo();
        if(a == null) return null;

        ConfigHelper ch = new ConfigHelper();
        /*
            ler o arquivo interno
         */
        return ch;
    }

    public static class Editor {

        private ConfigHelper myConfigHelper;

        private Editor(){}

        public static Editor tryLoad(){
            Editor e = new Editor();

            StringBuffer a = abrirArquivoExemplo();
            if(a == null) return e; // se falhar não retorna nulo, só vazio;


            e.myConfigHelper = new ConfigHelper();
            //lê o arquivo de fato

            return e;
        }

        //helper não salva pq não modifica, editor salva;
        public boolean save(){
            if(salvarDeuErrado) return false;
            return true;
        }

        //acesso público ao set
        public setConfig(int requestCode, int value){
            myConfigHelper.setConfig(requestCode,value);
        }

    }


}

/* para eu usar:
*
*   onCreate(...){
*       super(...);
*
*       ConfigHelper config = ConfigHelper.load();
*       if (config != null){
*           // Aplicar as configurações aqui
*       } else {
*           Intent intent = new Intent(this, ConfigMainActivity.class);
*           intent.putExtra("ConfigAndExit",true) // para saber que não entrou sozinho, eu que chamei
*           startActivityForResult(intent,12345); // para eu poder saber que você terminou (e dar um recreate() provavelmente)
*       }
*   }
*/

/* para você usar:
*
*   onCreate(...){
*       super(...)
*
*       ConfigHelper.Editor configEditor = ConfigHelper.Editor.tryLoad(); //editor nunca é nulo, no máximo vazio
*       // começar a configurar
*   }
 */
