public class Calculos {

    // Funcion para Calcular impuesto
    public double calcularImpuesto (double sueldoBruto){
        double impuesto = 0;
        if (sueldoBruto < 10000.0){
            impuesto = 0;
        }else if (sueldoBruto < 30000.0){
            impuesto = sueldoBruto * 0.1;
        }else if (sueldoBruto < 50000.0) {
            impuesto = sueldoBruto * 0.2;
        }else {
            impuesto = sueldoBruto * 0.35;
        }
        return Math.round(impuesto * 100) / 100.0;
    }

    // Funcion para Calcular la AFP
    public double calcularAFP(double sueldoBruto){
        return  Math.round(((sueldoBruto * 1.11)-sueldoBruto)*100)/100.0;
    }

    // Función para calcular Salud
    public double calcularSalud(double sueldoBruto){
        return Math.round(((sueldoBruto * 1.07)-sueldoBruto)*100)/100.0;
    }
    // Funcion para calcular el sueldo líquido
    public double calcularSueldoLiquido(double sueldoBruto, double impuesto, double afp, double salud){
        return Math.round((sueldoBruto - impuesto - afp - salud) * 100)/100.0;
    }
}
