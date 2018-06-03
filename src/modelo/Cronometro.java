package modelo;
/**/

public class Cronometro extends Thread {

    private boolean cronometroActivo;   //Booleano para el estado actual del cronometro
    private String texto; // hora mostrada en el cronometro
    Thread hilo;

    public Cronometro() {
        this.texto = "00:00:000";  //Empieza en ceros
    }

    public boolean isCronometroActivo() {
        return cronometroActivo;
    }

    public void setCronometroActivo(boolean cronometroActivo) {
        this.cronometroActivo = cronometroActivo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Thread getHilo() {
        return hilo;
    }

    public void setHilo(Thread hilo) {
        this.hilo = hilo;
    }

    public void run() {
        Integer minutos = 0, segundos = 0, milesimas = 0;
        //min es minutos, seg es segundos y mil es milesimas de segundo
        String min = "", seg = "", mil = "";
        try {
            //Mientras cronometroActivo sea verdadero entonces seguira
            //aumentando el tiempo
            while (cronometroActivo) {
                Thread.sleep(4);
                //Incrementamos 4 milesimas de segundo
                milesimas += 4;

                //Cuando llega a 1000 osea 1 segundo aumenta 1 segundo
                //y las milesimas de segundo de nuevo a 0
                if (milesimas == 1000) {
                    milesimas = 0;
                    segundos += 1;
                    //Si los segundos llegan a 60 entonces aumenta 1 los minutos
                    //y los segundos vuelven a 0
                    if (segundos == 60) {
                        segundos = 0;
                        minutos++;
                    }
                }

                //Esto solamente es estetica para que siempre este en formato
                //00:00:000
                if (minutos < 10) {
                    min = "0" + minutos;
                } else {
                    min = minutos.toString();
                }
                if (segundos < 10) {
                    seg = "0" + segundos;
                } else {
                    seg = segundos.toString();
                }

                if (milesimas < 10) {
                    mil = "00" + milesimas;
                } else if (milesimas < 100) {
                    mil = "0" + milesimas;
                } else {
                    mil = milesimas.toString();
                }

                //Colocamos en la etiqueta la informacion
                this.texto = min + ":" + seg + ":" + mil;
            }
        } catch (Exception e) {
        }
        //Cuando se reincie se coloca nuevamente en 00:00:000
        //this.texto = "00:00:000" ;
    }

    //Iniciar el cronometro poniendo cronometroActivo 
    //en verdadero para que entre en el while
    public void iniciarCronometro() {
        cronometroActivo = true;
        hilo = new Thread(this);
        hilo.start();
    }

    //Esto es para parar el cronometro
    public void pararCronometro() {
        cronometroActivo = false;
    }

}
