package equipo.recetario.saborio.model.data
import equipo.recetario.saborio.R
import equipo.recetario.saborio.model.domain.Ingrediente
import equipo.recetario.saborio.model.domain.Receta
import equipo.recetario.saborio.utilities.EtiquetasPredeterminadas

val RecetaPrueba = Receta(
    id = "6",
    nombre = "Cookies",
    descripcionBreve = "Galletas clásicas con chispas de chocolate.",
    fotografia = R.drawable.cookies,
    ingredientes = listOf(
        Ingrediente("Harina de trigo", 2.0, "tazas"),
        Ingrediente("Mantequilla sin sal", 115.0, "g"),
        Ingrediente("Azúcar morena", 0.75, "taza"),
        Ingrediente("Chispas de chocolate", 1.0, "taza"),
        Ingrediente("Huevo", 1.0, "pieza")
    ),
    pasosPreparacion = listOf(
        "Precalienta el horno a 180°C y forra un par de bandejas para hornear con papel pergamino.",
        "En un bol grande, bate la mantequilla a temperatura ambiente con el azúcar morena y el azúcar blanca hasta obtener una crema suave y esponjosa.",
        "Añade el huevo y el extracto de vainilla, integrando completamente. Luego, incorpora poco a poco la harina tamizada con el bicarbonato y una pizca de sal.",
        "Con la ayuda de una espátula, envuelve las chispas de chocolate oscuro en la masa hasta que estén bien distribuidas.",
        "Forma bolitas de masa del tamaño de una nuez, colócalas en las bandejas dejando espacio entre ellas y hornea de 10 a 12 minutos."
    ),
    etiquetas = listOf(
        EtiquetasPredeterminadas.POSTRE,
        EtiquetasPredeterminadas.PANADERIA,
        "Para compartir",
        "Al horno"
    ),
    enlaceWeb = null,
    esPublica = true,
    esSecreta = false,
    nombreAutor = "Ana Torres",
    calificacion = 4.5,
    esGuardada = false
)

var listaRecetas = listOf(
    Receta(
        id = "1",
        nombre = "Tagliatelle al Pesto de Albahaca Fresca",
        descripcionBreve = "Una oda a la cocina de Liguria: aromas herbáceos vibrantes emulsionados en frío.",
        fotografia = R.drawable.tagliatelle_pesto_albahaca,
        ingredientes = listOf(
            Ingrediente("Pasta tagliatelle fresca", 400.0, "g"),
            Ingrediente("Albahaca fresca (hojas tiernas)", 2.0, "tazas"),
            Ingrediente("Piñones tostados", 50.0, "g"),
            Ingrediente("Aceite de oliva virgen extra", 100.0, "ml"),
            Ingrediente("Dientes de ajo pelados", 2.0, "piezas")
        ),
        pasosPreparacion = listOf(
            "En una sartén pequeña y seca a fuego bajo, tuesta suavemente los 50g de piñones.",
            "En mortero o procesador de alimentos, tritura las 2 tazas de hojas de albahaca con los dientes de ajo, los piñones y sal.",
            "Hierve abundante agua salada en una olla grande. Sumerge los tagliatelle y cocina durante 3 a 4 minutos.",
            "Fuera del fuego directo, integra la pasta caliente con el pesto en un cuenco tibio."
        ),
        etiquetas = listOf(
            EtiquetasPredeterminadas.COMIDA,
            EtiquetasPredeterminadas.CENA,
            "Pasta",
            "Italiana"
        ),
        enlaceWeb = "chefsofia.com",
        esPublica = true,
        esSecreta = false,
        nombreAutor = "Chef Sofia Rossi",
        calificacion = 4.9,
        esGuardada = true
    ),

    Receta(
        id = "2",
        nombre = "Rigatoni al Pomodoro",
        descripcionBreve = "Clásica pasta italiana con salsa de tomate y queso.",
        fotografia = R.drawable.rigatoni_pomodoro,
        ingredientes = listOf(
            Ingrediente("Pasta Rigatoni", 200.0, "g"),
            Ingrediente("Puré de tomate natural", 400.0, "ml"),
            Ingrediente("Dientes de ajo", 2.0, "piezas"),
            Ingrediente("Albahaca fresca", 1.0, "puñado"),
            Ingrediente("Queso parmesano rallado", 50.0, "g")
        ),
        pasosPreparacion = listOf(
            "En una olla grande, pon a hervir abundante agua con un puñado generoso de sal marina.",
            "Agrega los rigatoni y cocina durante 10-12 minutos hasta que estén al dente.",
            "Mientras tanto, en una sartén amplia, calienta un poco de aceite de oliva y sofríe ajo finamente picado sin que se dore.",
            "Añade el puré de tomate natural, unas hojas de albahaca fresca y cocina a fuego lento por 15 minutos.",
            "Escurre la pasta, agrégala a la sartén con la salsa y mezcla vigorosamente. Sirve con queso parmesano rallado."
        ),
        etiquetas = listOf(
            EtiquetasPredeterminadas.COMIDA,
            EtiquetasPredeterminadas.CENA,
            "Pasta",
            "Italiana"
        ),
        enlaceWeb = null,
        esPublica = false,
        esSecreta = false,
        nombreAutor = "Tryndamere Gonzales",
        calificacion = 4.5,
        esGuardada = false
    ),

    Receta(
        id = "3",
        nombre = "Salmón Glaseado Bowl",
        descripcionBreve = "Bowl nutritivo con salmón, aguacate y quinoa.",
        fotografia = R.drawable.salmon_bowl,
        ingredientes = listOf(
            Ingrediente("Filete de Salmón", 2.0, "piezas"),
            Ingrediente("Quinoa cocida", 1.0, "taza"),
            Ingrediente("Salsa de soja", 3.0, "cucharadas"),
            Ingrediente("Aguacate", 0.5, "pieza"),
            Ingrediente("Semillas de sésamo", 1.0, "cucharada")
        ),
        pasosPreparacion = listOf(
            "Precalienta el horno a 200°C y prepara una bandeja con papel para hornear.",
            "En un tazón pequeño, mezcla salsa de soja, un toque de miel, jengibre rallado y aceite de sésamo para crear el glaseado.",
            "Marina los filetes de salmón en esta mezcla durante al menos 15 minutos en el refrigerador.",
            "Hornea el salmón durante 12-15 minutos, barnizándolo con el glaseado a la mitad de la cocción hasta que caramelice.",
            "Sirve el salmón caliente sobre una cama de quinoa cocida, acompañado de rodajas de aguacate y semillas de sésamo espolvoreadas."
        ),
        etiquetas = listOf(
            EtiquetasPredeterminadas.COMIDA,
            EtiquetasPredeterminadas.SALUDABLE,
            "Alta en proteína",
            "Pescado"
        ),
        enlaceWeb = null,
        esPublica = true,
        esSecreta = false,
        nombreAutor = "Chef Mario",
        calificacion = 4.5,
        esGuardada = true
    ),

    Receta(
        id = "4",
        nombre = "Avocado Toast",
        descripcionBreve = "Pan tostado con aguacate y huevo pochado.",
        fotografia = R.drawable.avocado_toast,
        ingredientes = listOf(
            Ingrediente("Pan de masa madre", 2.0, "rebanadas"),
            Ingrediente("Aguacate maduro", 1.0, "pieza"),
            Ingrediente("Huevos", 2.0, "piezas"),
            Ingrediente("Jugo de limón", 1.0, "cucharadita"),
            Ingrediente("Hojuelas de chile", 1.0, "pizca")
        ),
        pasosPreparacion = listOf(
            "Corta rebanadas gruesas de pan de masa madre y tuéstalas hasta que estén crujientes y doradas por los bordes.",
            "Corta el aguacate por la mitad, retira el hueso y machaca la pulpa en un bol con un chorrito de jugo de limón, sal y pimienta negra.",
            "En una olla pequeña con agua hirviendo y un chorrito de vinagre, crea un remolino y sumerge el huevo con cuidado para pocharlo por 3 minutos.",
            "Unta una capa generosa de la mezcla de aguacate sobre el pan tostado.",
            "Coloca el huevo pochado encima, espolvorea con hojuelas de chile y un hilo de aceite de oliva antes de servir."
        ),
        etiquetas = listOf(
            EtiquetasPredeterminadas.SALUDABLE,
            "15MIN",
        ),
        enlaceWeb = null,
        esPublica = true,
        esSecreta = false,
        nombreAutor = "Tryndamere Gonzales",
        calificacion = 3.8,
        esGuardada = false
    ),

    Receta(
        id = "5",
        nombre = "Tacos al Pastor",
        descripcionBreve = "Tradicionales tacos de cerdo adobado con piña.",
        fotografia = R.drawable.tacos_pastor,
        ingredientes = listOf(
            Ingrediente("Carne de cerdo (pierna)", 500.0, "g"),
            Ingrediente("Chiles guajillo", 3.0, "piezas"),
            Ingrediente("Piña en trozos", 1.0, "taza"),
            Ingrediente("Tortillas de maíz", 10.0, "piezas"),
            Ingrediente("Cilantro picado", 0.5, "taza")
        ),
        pasosPreparacion = listOf(
            "Prepara el adobo licuando chiles guajillo hidratados, pasta de achiote, vinagre de manzana, ajo, orégano y un trozo de piña.",
            "Corta la carne de cerdo en filetes delgados y báñalos completamente con el adobo. Deja reposar en el refrigerador por al menos 4 horas.",
            "En una sartén muy caliente o parrilla, asa la carne fileteada hasta que esté bien cocida y con los bordes ligeramente dorados.",
            "Pica la carne finamente y calienta las tortillas de maíz en la misma sartén para que absorban el sabor del adobo.",
            "Sirve la carne sobre las tortillas, acompañando con cilantro picado, cebolla finamente cortada, trozos de piña asada y salsa al gusto."
        ),
        etiquetas = listOf(
            EtiquetasPredeterminadas.COMIDA,
            EtiquetasPredeterminadas.CENA,
            "Comida Mexicana"
        ),
        enlaceWeb = null,
        esPublica = true,
        esSecreta = false,
        nombreAutor = "Taquería El Trompo",
        calificacion = 5.0,
        esGuardada = true
    ),

    Receta(
        id = "6",
        nombre = "Cookies",
        descripcionBreve = "Galletas clásicas con chispas de chocolate.",
        fotografia = R.drawable.cookies,
        ingredientes = listOf(
            Ingrediente("Harina de trigo", 2.0, "tazas"),
            Ingrediente("Mantequilla sin sal", 115.0, "g"),
            Ingrediente("Azúcar morena", 0.75, "taza"),
            Ingrediente("Chispas de chocolate", 1.0, "taza"),
            Ingrediente("Huevo", 1.0, "pieza")
        ),
        pasosPreparacion = listOf(
            "Precalienta el horno a 180°C y forra un par de bandejas para hornear con papel pergamino.",
            "En un bol grande, bate la mantequilla a temperatura ambiente con el azúcar morena y el azúcar blanca hasta obtener una crema suave y esponjosa.",
            "Añade el huevo y el extracto de vainilla, integrando completamente. Luego, incorpora poco a poco la harina tamizada con el bicarbonato y una pizca de sal.",
            "Con la ayuda de una espátula, envuelve las chispas de chocolate oscuro en la masa hasta que estén bien distribuidas.",
            "Forma bolitas de masa del tamaño de una nuez, colócalas en las bandejas dejando espacio entre ellas y hornea de 10 a 12 minutos."
        ),
        etiquetas = listOf(
            EtiquetasPredeterminadas.POSTRE,
            EtiquetasPredeterminadas.PANADERIA,
            "Al Horno",
            "Chocolate"
        ),
        enlaceWeb = null,
        esPublica = true,
        esSecreta = false,
        nombreAutor = "Ana Torres",
        calificacion = 4.5,
        esGuardada = false
    ),

    Receta(
        id = "7",
        nombre = "Açai Bowl",
        descripcionBreve = "Bowl de frutas tropicales con base de açai.",
        fotografia = R.drawable.acai_bowl,
        ingredientes = listOf(
            Ingrediente("Pulpa de Açai congelada", 1.0, "paquete"),
            Ingrediente("Plátano", 1.0, "pieza"),
            Ingrediente("Leche de almendras", 0.5, "taza"),
            Ingrediente("Fresas frescas", 5.0, "piezas"),
            Ingrediente("Granola", 2.0, "cucharadas")
        ),
        pasosPreparacion = listOf(
            "Retira los paquetes de pulpa de açai del congelador y déjalos reposar a temperatura ambiente por unos 5 minutos para que se ablanden ligeramente.",
            "En una licuadora de alta potencia, coloca la pulpa de açai, medio plátano congelado y un chorrito de leche de almendras o agua de coco.",
            "Licúa a velocidad media, empujando los ingredientes hacia las cuchillas si es necesario, hasta obtener una textura espesa y suave.",
            "Vierte la mezcla inmediatamente en un tazón amplio y profundo.",
            "Decora artísticamente la superficie con rodajas frescas de fresa, el resto del plátano, granola crujiente, hojuelas de coco y semillas de chía."
        ),
        etiquetas = listOf(
            EtiquetasPredeterminadas.DESAYUNO,
            "Frutas"
        ),
        enlaceWeb = null,
        esPublica = true,
        esSecreta = false,
        nombreAutor = "FitLife Recipes",
        calificacion = 4.5,
        esGuardada = false
    ),

    Receta(
        id = "8",
        nombre = "Brownies",
        descripcionBreve = "La receta familiar secreta que no comparto con nadie.",
        fotografia = R.drawable.brownie,
        ingredientes = listOf(
            Ingrediente("Chocolate oscuro (70%)", 200.0, "g"),
            Ingrediente("Mantequilla", 100.0, "g"),
            Ingrediente("Azúcar", 1.5, "tazas"),
            Ingrediente("Huevos", 3.0, "piezas"),
            Ingrediente("Harina de trigo", 0.5, "taza")
        ),
        pasosPreparacion = listOf(
            "Precalienta tu horno a 175°C y prepara un molde cuadrado engrasándolo ligeramente y cubriéndolo con papel para hornear.",
            "En un recipiente a baño maría, derrite el chocolate oscuro de alta calidad junto con la mantequilla, removiendo hasta obtener una mezcla brillante y sedosa.",
            "Retira del fuego y añade el azúcar, mezclando enérgicamente. Incorpora los huevos uno por uno, batiendo bien después de cada adición.",
            "Agrega la harina y el cacao en polvo tamizados, mezclando con movimientos envolventes justo hasta que desaparezcan los grumos blancos.",
            "Vierte la masa en el molde, nivela la superficie y hornea por 25-30 minutos; el centro debe quedar ligeramente húmedo."
        ),
        etiquetas = listOf(
            EtiquetasPredeterminadas.POSTRE,
            EtiquetasPredeterminadas.PANADERIA
        ),
        enlaceWeb = null,
        esPublica = false,
        esSecreta = true,
        nombreAutor = "Tryndamere Gonzales",
        calificacion = 5.0,
        esGuardada = false
    ),

    Receta(
        id = "9",
        nombre = "Chilaquiles Rojos",
        descripcionBreve = "Desayuno tradicional mexicano con salsa roja picante.",
        fotografia = R.drawable.chilaquiles,
        ingredientes = listOf(
            Ingrediente("Totopos de maíz", 300.0, "g"),
            Ingrediente("Tomates rojos", 4.0, "piezas"),
            Ingrediente("Chiles serranos", 2.0, "piezas"),
            Ingrediente("Cebolla blanca", 0.25, "pieza"),
            Ingrediente("Queso fresco", 100.0, "g")
        ),
        pasosPreparacion = listOf(
            "En una olla, pon a hervir los tomates rojos junto con los chiles serranos y un diente de ajo hasta que los tomates cambien de color y estén suaves.",
            "Pasa los ingredientes hervidos a la licuadora, añade un trozo de cebolla, sal y licúa hasta obtener una salsa roja de textura tersa.",
            "Calienta una cucharada de aceite en una cacerola amplia, vierte la salsa licuada y deja hervir a fuego medio-bajo por unos 10 minutos para que los sabores se integren.",
            "Un par de minutos antes de servir, sumerge los totopos de maíz en la salsa caliente, asegurándote de que todos queden cubiertos, pero retirándolos pronto para que se mantengan crujientes.",
            "Sirve inmediatamente decorando con crema ácida, queso fresco desmoronado, aros de cebolla morada y unas hojas de cilantro."
        ),
        etiquetas = listOf(
            EtiquetasPredeterminadas.DESAYUNO,
            "Comida Mexicana",
            "Picante"
        ),
        enlaceWeb = null,
        esPublica = true,
        esSecreta = false,
        nombreAutor = "Abuela Carmen",
        calificacion = 4.8,
        esGuardada = true
    ),

    Receta(
        id = "10",
        nombre = "Ensalada César",
        descripcionBreve = "Ensalada fresca con aderezo casero y crutones.",
        fotografia = R.drawable.ensalada,
        ingredientes = listOf(
            Ingrediente("Lechuga orejona", 1.0, "pieza"),
            Ingrediente("Pan rústico en cubos", 1.0, "taza"),
            Ingrediente("Queso parmesano", 50.0, "g"),
            Ingrediente("Aceite de oliva", 3.0, "cucharadas"),
            Ingrediente("Limón (jugo)", 1.0, "pieza")
        ),
        pasosPreparacion = listOf(
            "Lava meticulosamente las hojas de lechuga orejona, desinféctalas y escúrrelas por completo usando un centrifugador de vegetales para asegurar que estén crujientes.",
            "Para los crutones, corta pan rústico en cubos, rocíalos con aceite de oliva, ajo en polvo y hornea a 200°C hasta que estén dorados y secos.",
            "Prepara el aderezo tradicional triturando en un bol filetes de anchoa, ajo, yemas de huevo, mostaza Dijon, jugo de limón y batiendo lentamente con aceite de oliva hasta emulsionar.",
            "Rompe las hojas de lechuga con las manos en trozos del tamaño de un bocado y colócalas en una ensaladera grande.",
            "Vierte el aderezo sobre la lechuga, mezcla suavemente para cubrir cada hoja, añade los crutones y termina con abundante queso parmesano recién rallado."
        ),
        etiquetas = listOf(
            EtiquetasPredeterminadas.ENSALADA,
            EtiquetasPredeterminadas.SALUDABLE,
            EtiquetasPredeterminadas.VEGETARIANA,
            EtiquetasPredeterminadas.CENA,
            "Baja en calorías"
        ),
        enlaceWeb = null,
        esPublica = true,
        esSecreta = false,
        nombreAutor = "Tryndamere Gonzales",
        calificacion = 4.0,
        esGuardada = false
    )
)

fun getMisRecetas(miNombre: String): List<Receta> {
    return listaRecetas.filter { it.nombreAutor == miNombre }
}

fun getRecetasExplorar(miNombre: String): List<Receta> {
    return listaRecetas.filter { it.esPublica && it.nombreAutor != miNombre }
}

fun getRecetasGuardadas(): List<Receta> {
    return listaRecetas.filter { it.esGuardada }
}

fun buscarPorNombre(listaActual: List<Receta>, query: String): List<Receta> {
    if (query.isBlank()) return listaActual
    return listaActual.filter { it.nombre.contains(query, ignoreCase = true) }
}

fun filtrarPorEtiqueta(listaActual: List<Receta>, etiquetaSeleccionada: String): List<Receta> {
    if (etiquetaSeleccionada.equals("Todas", ignoreCase = true)) {
        return listaActual
    }

    if (etiquetaSeleccionada.equals("Otras", ignoreCase = true) || etiquetaSeleccionada.equals("Otros", ignoreCase = true)) {
        val predeterminadas = EtiquetasPredeterminadas.listaEtiquetas

        return listaActual.filter { receta ->
            receta.etiquetas.any { etiqueta ->
                predeterminadas.none { predeterminada ->
                    predeterminada.equals(etiqueta, ignoreCase = true)
                }
            }
        }
    }

    return listaActual.filter { receta ->
        receta.etiquetas.any { it.equals(etiquetaSeleccionada, ignoreCase = true) }
    }
}

fun guardadoGlobalDeRecetas(nombreReceta: String) {
    listaRecetas = listaRecetas.map { receta ->
        if (receta.nombre == nombreReceta) {
            receta.copy(esGuardada = !receta.esGuardada)
        } else {
            receta
        }
    }
}