package presentacion.controlador;

public class Evento {
	public static final int ELECCION_INICIO_PROGRAMA = 0;
	public static final int INICIO_PROGRAMA = 1;
	public static final int INICIO_PROGRAMA_JPA = 2;

	// ERRORES
	public static final int ERROR_GENERICO = -1;
	public static final int ID_NO_EXISTENTE = -2;
	public static final int DNI_EXISTENTE = -3;
	public static final int REACTIVACION = -4;
	public static final int SECCION_NO_EXISTENTE = -5;
	public static final int YA_INACTIVO = -6;
	public static final int SECCION_NO_ACTIVA = -7;
	public static final int NOMBRE_EXISTENTE = -8;
	public static final int SECCION_CON_TRABAJADOR = -9;
	public static final int PROVEEDOR_NO_EXISTENTE = -10;
	public static final int PROVEEDOR_NO_ACTIVO = -11;
	public static final int TIPO_EXISTENTE = -12;
	public static final int PRODUCTO_NO_EXISTENTE = -13;
	public static final int PRODUCTO_NO_ACTIVO = -14;
	public static final int NO_STOCK = -15;
	public static final int TRABAJADOR_NO_EXISTENTE = -16;
	public static final int TRABAJADOR_NO_ACTIVO = -17;
	public static final int LINEA_VACIA = -18;
	public static final int NIF_EXISTENTE = -19;
	public static final int PROVEEDOR_CON_PRODUCTO = -20;
	public static final int PRODUCTO_VINCULADO = -21;
	public static final int CODIGO_EXISTENTE = -22;
	public static final int ID_YA_EXISTENTE = -23;
	public static final int DATOS_INCORRECTOS = -24;
	public static final int ENTIDAD_YA_INACTIVA= -25;
	public static final int EXPOSICION_CON_GUIAS= -26;
	public static final int EXPOSICION_CON_OBRAS= -27;
	
	public static final int ENTIDAD_NO_ACTIVA= -28;
	public static final int ENTIDAD_NO_EXISTE = -29;
	
	public static final int GUIA_NO_EXISTENTE = -30;
	public static final int GUIA_NO_ACTIVA = -31;
	
	public static final int ACTIVIDAD_NO_EXISTENTE = -32;
	public static final int CODIGO_ACTIVIDAD_EXISTENTE = -32;
	public static final int TIPO_INCORRECTO = -33;
	public static final int FECHA_ANTIGUA= -34;
	public static final int OBRA_EX_AUTOR_Y_NOMBRE=-35;
	public static final int USUARIO_CON_ACTIVIDADES=-36;
	public static final int GUIA_OCUPADO=-37;
	public static final int USUARIO_YA_VINCULADO=-38;
	public static final int ACTIVIDAD_NO_ACTIVA=-39;
	public static final int YA_DESVINCULADO=-40;
	
	public static final int ALTA_LINEA_VENTA = 10;
	public static final int BAJA_LINEA_VENTA = 11;
	public static final int MOSTRAR_CARRITO = 12;
	
	public static final int ALTA_LINEA_GUIA = 13;
	public static final int VINCULADO_OK = 14;
	public static final int BAJA_LINEA_GUIA = 15;
	public static final int DESVINCULADO_OK = 16;
	
	public static final int ALTA_LINEA_ACTIVIDAD = 17;
	public static final int BAJA_LINEA_ACTIVIDAD = 18;


	// VENTA
	public static final int VENTA = 100;
	public static final int MOSTRAR_ALTA_VENTA = 101;
	public static final int ALTA_VENTA = 102;
	public static final int MOSTRAR_BAJA_VENTA = 103;
	public static final int BAJA_VENTA = 104;
	public static final int MOSTRAR_MOSTRAR_VENTA = 105;
	public static final int MOSTRAR_VENTA = 106;
	public static final int MOSTRAR_LISTAR_VENTA = 107;
	public static final int LISTAR_VENTA = 110;
	public static final int LISTAR_VENTA_POR_TRABAJADOR = 117;
	public static final int LISTAR_VENTA_POR_PRODUCTO = 118;
	public static final int LISTAR_VENTA_POR_PRODUCTO_Y_TRABAJADOR = 119;

	// PRODUCTO
	public static final int PRODUCTO = 200;
	public static final int MOSTRAR_ACTUALIZAR_PRODUCTO = 201;
	public static final int ACTUALIZAR_PRODUCTO = 202;
	public static final int MOSTRAR_ALTA_PRODUCTO = 203;
	public static final int ALTA_PRODUCTO = 204;
	public static final int MOSTRAR_BAJA_PRODUCTO = 205;
	public static final int BAJA_PRODUCTO = 206;
	public static final int MOSTRAR_MOSTRAR_PRODUCTO = 207;
	public static final int MOSTRAR_PRODUCTO = 208;
	public static final int MOSTRAR_LISTAR_PRODUCTO = 209;
	public static final int LISTAR_PRODUCTO = 210;
	public static final int MOSTRAR_PRODUCTO_MAS_VENDIDO = 211;
	public static final int MOSTRAR_MOSTRAR_PRODUCTO_MAS_VENDIDO = 212;

	// PROVEEDOR
	public static final int PROVEEDOR = 300;
	public static final int MOSTRAR_ACTUALIZAR_PROVEEDOR = 301;
	public static final int ACTUALIZAR_PROVEEDOR = 302;
	public static final int MOSTRAR_ALTA_PROVEEDOR = 303;
	public static final int ALTA_PROVEEDOR = 304;
	public static final int MOSTRAR_BAJA_PROVEEDOR = 305;
	public static final int BAJA_PROVEEDOR = 306;
	public static final int MOSTRAR_MOSTRAR_PROVEEDOR = 307;
	public static final int MOSTRAR_PROVEEDOR = 308;
	public static final int LISTAR_PROVEEDOR = 309;
	public static final int MOSTRAR_LISTAR_PROVEEDOR = 310;

	// SECCION

	public static final int SECCION = 400;
	public static final int MOSTRAR_ACTUALIZAR_SECCION = 401;
	public static final int ACTUALIZAR_SECCION = 402;
	public static final int MOSTRAR_ALTA_SECCION = 403;
	public static final int ALTA_SECCION = 404;
	public static final int MOSTRAR_BAJA_SECCION = 405;
	public static final int BAJA_SECCION = 406;
	public static final int MOSTRAR_MOSTRAR_SECCION = 407;
	public static final int MOSTRAR_SECCION = 408;
	public static final int MOSTRAR_LISTAR_SECCION = 409;
	public static final int LISTAR_SECCION = 410;

	// TRABAJADOR
	public static final int TRABAJADOR = 500;
	public static final int MOSTRAR_ACTUALIZAR_TRABAJADOR = 501;
	public static final int ACTUALIZAR_TRABAJADOR = 502;
	public static final int MOSTRAR_ALTA_TRABAJADOR = 503;
	public static final int ALTA_TRABAJADOR = 504;
	public static final int MOSTRAR_BAJA_TRABAJADOR = 505;
	public static final int BAJA_TRABAJADOR = 506;
	public static final int MOSTRAR_MOSTRAR_TRABAJADOR = 507;
	public static final int MOSTRAR_TRABAJADOR = 508;
	public static final int MOSTRAR_LISTAR_TRABAJADOR = 509;
	public static final int LISTAR_TRABAJADOR = 510;
	public static final int MOSTRAR_TRABAJADOR_CON_MAS_VENTAS = 511;
	public static final int MOSTRAR_MOSTRAR_TRABAJADOR_CON_MAS_VENTAS = 512;

	// INGREDIENTE
	public static final int INGREDIENTE = 600;
	public static final int MOSTRAR_ACTUALIZAR_INGREDIENTE = 601;
	public static final int ACTUALIZAR_INGREDIENTE = 602;
	public static final int MOSTRAR_ALTA_INGREDIENTE = 603;
	public static final int ALTA_INGREDIENTE = 604;
	public static final int MOSTRAR_BAJA_INGREDIENTE = 605;
	public static final int BAJA_INGREDIENTE = 606;
	public static final int MOSTRAR_MOSTRAR_INGREDIENTE = 607;
	public static final int MOSTRAR_INGREDIENTE = 608;
	public static final int MOSTRAR_LISTAR_INGREDIENTE = 609;
	public static final int LISTAR_INGREDIENTE = 610;

	// OBRA
	public static final int OBRA = 700;
	public static final int MOSTRAR_ACTUALIZAR_OBRA = 701;
	public static final int ACTUALIZAR_OBRA = 702;
	public static final int MOSTRAR_ALTA_OBRA = 703;
	public static final int ALTA_OBRA = 704;
	public static final int MOSTRAR_BAJA_OBRA = 705;
	public static final int BAJA_OBRA = 706;
	public static final int MOSTRAR_MOSTRAR_OBRA = 707;
	public static final int MOSTRAR_OBRA = 708;
	public static final int MOSTRAR_LISTAR_OBRA = 709;
	public static final int LISTAR_OBRA = 710;
	public static final int LISTAR_OBRA_POR_EXPOSICION=711;
	public static final int MOSTRAR_MOSTRAR_COSTE_OBRA = 712;
	public static final int MOSTRAR_COSTE_OBRA = 713;
	


	// EXPOSICION
	public static final int EXPOSICION = 800;
	public static final int MOSTRAR_ACTUALIZAR_EXPOSICION = 801;
	public static final int ACTUALIZAR_EXPOSICION = 802;
	public static final int MOSTRAR_ALTA_EXPOSICION = 803;
	public static final int ALTA_EXPOSICION = 804;
	public static final int MOSTRAR_BAJA_EXPOSICION = 805;
	public static final int BAJA_EXPOSICION = 806;
	public static final int MOSTRAR_MOSTRAR_EXPOSICION = 807;
	public static final int MOSTRAR_EXPOSICION = 808;
	public static final int MOSTRAR_LISTAR_EXPOSICION = 809;
	public static final int LISTAR_EXPOSICION = 810;
	public static final int MOSTRAR_MOSTRAR_COSTE_EXPOSICION = 811;
	public static final int MOSTRAR_COSTE_EXPOSICION = 812;

	// GUIA
	public static final int GUIA = 900;
	public static final int MOSTRAR_ACTUALIZAR_GUIA = 901;
	public static final int ACTUALIZAR_GUIA = 902;
	public static final int MOSTRAR_ALTA_GUIA = 903;
	public static final int ALTA_GUIA = 904;
	public static final int MOSTRAR_BAJA_GUIA = 905;
	public static final int BAJA_GUIA = 906;
	public static final int MOSTRAR_MOSTRAR_GUIA = 907;
	public static final int MOSTRAR_GUIA = 908;
	public static final int MOSTRAR_LISTAR_GUIA = 909;
	public static final int LISTAR_GUIA = 910;
	public static final int MOSTRAR_VINCULAR_GUIA = 911;
	public static final int LISTAR_GUIA_POR_EXPOSICION = 912;
	
	// USUARIO
	public static final int USUARIO = 1000;
	public static final int MOSTRAR_ACTUALIZAR_USUARIO = 1001;
	public static final int ACTUALIZAR_USUARIO = 1002;
	public static final int MOSTRAR_ALTA_USUARIO = 1003;
	public static final int ALTA_USUARIO = 1004;
	public static final int MOSTRAR_BAJA_USUARIO = 1005;
	public static final int BAJA_USUARIO = 1006;
	public static final int MOSTRAR_MOSTRAR_USUARIO = 1007;
	public static final int MOSTRAR_USUARIO = 1008;
	public static final int MOSTRAR_LISTAR_USUARIO = 1009;
	public static final int LISTAR_USUARIO = 1010;
	public static final int LISTAR_USUARIO_POR_GUIA = 1011;
	
	// ACTIVIDAD
	public static final int ACTIVIDAD = 1100;
	public static final int MOSTRAR_ACTUALIZAR_ACTIVIDAD = 1101;
	public static final int ACTUALIZAR_ACTIVIDAD = 1102;
	public static final int MOSTRAR_ALTA_ACTIVIDAD = 1103;
	public static final int ALTA_ACTIVIDAD = 1104;
	public static final int MOSTRAR_BAJA_ACTIVIDAD = 1105;
	public static final int BAJA_ACTIVIDAD = 1106;
	public static final int MOSTRAR_MOSTRAR_ACTIVIDAD = 1107;
	public static final int MOSTRAR_ACTIVIDAD = 1108;
	public static final int MOSTRAR_LISTAR_ACTIVIDAD = 1109;
	public static final int LISTAR_ACTIVIDAD = 1110;
	public static final int MOSTRAR_VINCULAR_ACTIVIDAD = 1111;
	public static final int LISTAR_ACTIVIDAD_POR_USUARIO = 1112;
	
	

}