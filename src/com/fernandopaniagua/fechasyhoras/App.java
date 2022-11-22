package com.fernandopaniagua.fechasyhoras;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class App {

	public static void main(String[] args) {
		//java.util.Date es anterior a la versión 1.8
		Date hoyOld = new Date();
		System.out.println(hoyOld);//SALIDA: Mon Nov 21 20:34:06 CET 2022
		
		//A partir de v1.8 se utiliza el paquete java.time. 
		//LocalDate --> Fecha local (no hay zona horaria)
		LocalDate hoy = LocalDate.now();
		System.out.println(hoy);//SALIDA: 2022-11-21
		//LocalTime --> Hora local (no hay zona horaria)
		LocalTime ahora = LocalTime.now();
		System.out.println(ahora);//SALIDA 20:35:59.997093900
		//LocalDateTime --> Fecha y hora local (no hay zona horaria)
		LocalDateTime hoyYAhora = LocalDateTime.now();
		System.out.println(hoyYAhora);//SALIDA 2022-11-21T20:37:55.692972500
		//ZonedDateTime --> Fecha y hora local (incluye zona horaria)
		ZonedDateTime zdt = ZonedDateTime.now();
		System.out.println(zdt);//2022-11-21T20:38:44.886953200+01:00[Europe/Madrid]
		
		
		//El método of permite obtener instancias de las clases temporales a partir de información concreta
		LocalDate nacimientoTouring1 = LocalDate.of(1912, Month.JUNE, 23);
		LocalDate nacimientoTouring2 = LocalDate.of(1912, 6, 23);
		//El método parse es otro método para obtener instancias de fechas
		LocalDate nacimientoDeHenriGouraud = LocalDate.parse("1944-01-15");
		//Los mismos métodos funciona en LocalTime y LocalDateTime
		LocalTime horaFeliz1 = LocalTime.of(20, 30);
		LocalTime horaFeliz2 = LocalTime.parse("20:30");
		LocalDateTime citaUvasNochevieja = LocalDateTime.of(2022, 12, 31, 23, 55);
		
		//ZoneId
		System.out.println(ZoneId.systemDefault());//SALIDA Europe/Madrid
		//Obtención de los ids de zona disponibles
		ZoneId.getAvailableZoneIds().forEach(
				zona -> {
					System.out.println(zona);
				}
		);
		
		//Operaciones con FECHAS. Aplicables a LocalDate, LocalDateTime y ZonedDateTime
		LocalDate today = LocalDate.now();
		System.out.println(today);//SALIDA: 2022-11-21
		LocalDate fechaExamen = today.plusDays(5);
		System.out.println(fechaExamen);//SALIDA: 2022-11-26
		fechaExamen = today.plusDays(10);
		System.out.println(fechaExamen);//SALIDA: 2022-12-01
		LocalDate ayer = today.minusDays(1);
		System.out.println(ayer);//SALIDA: 2022-11-20
		//¿Tiene en cuenta los años bisiestos?
		LocalDate ultimoDiaDeFebrero = LocalDate.of(2023, Month.FEBRUARY, 28);//2023 no es bisiesto
		System.out.println(ultimoDiaDeFebrero.plusDays(1));//SALIDA 2023-03-01
		ultimoDiaDeFebrero = LocalDate.of(2024, Month.FEBRUARY, 28);//2024 es bisiesto
		System.out.println(ultimoDiaDeFebrero.plusDays(1));//SALIDA 2024-02-29
		//Hay métodos plus(), plusWeeks(), plusMonths(), plusYears()
		//Hay métodos minus(), minusWeeks(), minusMonths(), minusYears() 
		
		//Operaciones con HORAS (métodos análogos a los de fechas). Aplicables a LocalTime, LocalDateTime y ZonedDateTime
		LocalTime now = LocalTime.now();
		System.out.println(now);//SALIDA 21:07:02.155106400
		System.out.println(now.plusMinutes(53));//SALIDA 22:00:02.155106400
		
		//Los métodos de incremento y decremento encadenables
		LocalDateTime todayAndNow = LocalDateTime.now();
		System.out.println(todayAndNow);//SALIDA: 2022-11-21T21:11:08.392074
		LocalDateTime fechaFinDelMundo = todayAndNow.plusYears(40).plusMonths(3).plusDays(2).plusHours(2);
		System.out.println(fechaFinDelMundo);//SALIDA: 2063-02-23T23:11:08.392074
		
		//Epoch es 1-1-1970. 
		System.out.println(today.toEpochDay());//Número de días que han transcurrido desde 1/1/1970
		System.out.println(LocalDate.of(1970, 1, 30).toEpochDay());//Número de días que han transcurrido desde 1/1/1970
		
		//Métodos de comparación fechas y horas isAfter - isBefore - isEqual - equals
		if (nacimientoDeHenriGouraud.isAfter(nacimientoTouring1)) {
			System.out.println("Gouraud es posterior a Touring");//Es posterior --> Muestra el texto
		}
		if (LocalTime.now().isBefore(LocalTime.of(22,0))) {
			System.out.println("Aún no se ha terminado la sesión");//Dependiendo de la hora, muestra el mensaje
		}
		//Ejercicio propuesto
		/*
		 * El día 12 de noviembre de 2022 el usuario solicitó un certificado.
		 * El certificado tiene una validez de 5 días.
		 * ¿Es válido el certificado en el día de hoy?
		 */
		LocalDate fechaDia = LocalDate.now();
		LocalDate fechaCertificado = LocalDate.of(2022, 11, 16);
		LocalDate fechaTope = fechaCertificado.plusDays(5);
		
		//En este caso se puede utilizar isEqual y equals indistintamente
		if (fechaTope.isAfter(fechaDia) || fechaTope.equals(fechaDia)) {
			System.out.println("El certificado está vigente");
		} else {
			System.out.println("El certificado ha expirado");
		}
		
		//Clase Period-->Representa un periodo de tiempo (dias, meses, semanas y años)
		
		//Obtención de un Period mediante el método between
		Period plazoDisponible = Period.between(fechaCertificado, fechaTope);
		System.out.println(plazoDisponible);//SALIDA P5D
		plazoDisponible = plazoDisponible.plusDays(1);
		System.out.println(plazoDisponible);//SALIDA P6D
		
		Period _8dias = Period.ofDays(8);
		System.out.println("8 DIAS" + _8dias);//SALIDA P8D
		
		Period _3years = Period.ofYears(5);
		System.out.println("3 AÑOS" + _3years);//SALIDA P3Y
		
		Period _period3Y2M2D = Period.parse("P3Y2M21D");
		System.out.println(_period3Y2M2D);
		
		//Periodos tienen métodos de acceso a sus partes:
		System.out.println(_period3Y2M2D.getDays());
		System.out.println(String.format("Faltan %d años, %d meses y %d días para el evento", 
				_period3Y2M2D.getYears(),
				_period3Y2M2D.getMonths(),
				_period3Y2M2D.getDays()));
		//Salida => Faltan 3 años, 2 meses y 21 días para el evento
		_period3Y2M2D = _period3Y2M2D.minusDays(1);
		System.out.println(String.format("Faltan %d años, %d meses y %d días para el evento", 
				_period3Y2M2D.getYears(),
				_period3Y2M2D.getMonths(),
				_period3Y2M2D.getDays()));
		//Salida => Faltan 3 años, 2 meses y 20 días para el evento
		
		//Clase Duration-->Representa un periodo de tiempo (nanosegundos, milisegundos, segundos, minutos, horas, dias)  
		Duration duration = Duration.ofSeconds(10);
		Duration tiempoRestante = Duration.between(LocalTime.now(), LocalTime.now().plusHours(3).plusMinutes(15));//PT3H15M
		System.out.println(String.format("Faltan %d horas y %d minutos para terminar la actividad", 
				(int)(tiempoRestante.getSeconds()/3600),
				(int)((tiempoRestante.getSeconds()%3600)/60)));
		
		//Enumeración ChronoUnit - Dispone de métodos para saber cuántas unidades temporales hay entre fechas
		hoy.plus(10, ChronoUnit.DAYS);//Equivalente a hoy.plusDays(10);
		
		fechaCertificado = LocalDate.of(2022, 10, 19);
		System.out.println("Entre fecha certificado y fecha del día hay " + 
				ChronoUnit.DAYS.between(fechaCertificado, fechaDia) +
				" días");
		System.out.println("Entre fecha certificado y fecha del día hay " +
				ChronoUnit.WEEKS.between(fechaCertificado, fechaDia) +
				" semanas");
		
		//Instant --> Representa un instante de tiempo
		
		//FORMATEADO DE FECHAS -- Se realiza utilizando la clase DateTimeFormatter
		LocalDate elDiaDeHoy = LocalDate.now();
		System.out.println(elDiaDeHoy);
		
		DateTimeFormatter dtfISODATE = DateTimeFormatter.BASIC_ISO_DATE;
		System.out.println(elDiaDeHoy.format(dtfISODATE));
		
		DateTimeFormatter dtfPropio1 = DateTimeFormatter.ofPattern("d/M/u");
		System.out.println(elDiaDeHoy.format(dtfPropio1));
		DateTimeFormatter dtfPropio2 = DateTimeFormatter.ofPattern("d/M/YY");
		System.out.println(elDiaDeHoy.format(dtfPropio2));
		DateTimeFormatter dtfPropio3 = DateTimeFormatter.ofPattern("d 'de' M 'de' YYYY");
		System.out.println(elDiaDeHoy.format(dtfPropio3));
		DateTimeFormatter dtfPropio4 = DateTimeFormatter.ofPattern("d 'de' MMM 'de' YYYY");
		System.out.println(elDiaDeHoy.format(dtfPropio4));
		
	} 

}
