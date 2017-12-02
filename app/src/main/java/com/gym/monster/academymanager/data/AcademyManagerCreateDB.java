package com.gym.monster.academymanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by victor on 19/03/17.
 */

public class AcademyManagerCreateDB extends SQLiteOpenHelper {
    public static final String NOME_DB = "AcademyManager";
    private static final int VERSAO_DB = 23; //Last version 16

    public AcademyManagerCreateDB(Context context){
        super(context, NOME_DB, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USUARIO(" +
                "ID_USUARIO INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOME       VARCHAR(150)," +
                "EMAIL      VARCHAR(150)," +
                "ALTURA     INTEGER," +
                "PESO       FLOAT(4,3));");

        db.execSQL("CREATE TABLE REL_TIPO_TREINO(" +
                "ID_TIPO_TREINO  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOME_TIPO_TREINO       VARCHAR(100));");

        db.execSQL("CREATE TABLE TREINO(" +
                "ID_TREINO  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOME       VARCHAR(100)," +
                "ID_TIPO_TREINO INTEGER," +
                "FOREIGN KEY(ID_TIPO_TREINO) REFERENCES REL_TIPO_TREINO(ID_TIPO_TREINO) ON UPDATE CASCADE);");

        db.execSQL("CREATE TABLE CATEGORIA_EXERCICIO(" +
                "ID_CATEGORIA   INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOME           VARCHAR(10));" );

        db.execSQL("CREATE TABLE EXERCICIO(" +
                "ID_EXERCICIO   INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ID_CATEGORIA   INTEGER NOT NULL," +
                "NOME           VARCHAR(100)," +
                "QTD_SERIES     INTEGER," +
                "QTD_REPETICOES INTEGER," +
                "PESO           FLOAT(4,3)," +
                "DESCRICAO      VARCHAR(999)," +
                "FOREIGN KEY(ID_CATEGORIA) REFERENCES CATEGORIA_EXERCICIO(ID_CATEGORIA) ON UPDATE CASCADE);");

        db.execSQL("CREATE TABLE REL_TREINO_EXERCICIO(" +
                "ID_EXERCICIO   INTEGER NOT NULL," +
                "ID_TREINO      INTEGER NOT NULL," +
                "FOREIGN KEY(ID_EXERCICIO) REFERENCES EXERCICIO(ID_EXERCICIO) ON UPDATE CASCADE," +
                "FOREIGN KEY(ID_TREINO) REFERENCES TREINO(ID_TREINO) ON UPDATE CASCADE);" );

        db.execSQL("CREATE TABLE DIETA(" +
                "ID_DIETA       INTEGER PRIMARY KEY AUTOINCREMENT," +
                "DATA           DATE," +
                "QTD_CALORIAS   INTEGER);" );

        db.execSQL("CREATE TABLE ALIMENTO(" +
                "ID_ALIMENTO    INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOME           VARCHAR(100)," +
                "VLR_CALORICO   INT);" );

        db.execSQL("CREATE TABLE REL_DIETA_ALIMENTO(" +
                "ID_DIETA       INTEGER NOT NULL," +
                "ID_ALIMENTO    INTEGER NOT NULL," +
                "FOREIGN KEY(ID_DIETA) REFERENCES DIETA(ID_DIETA)," +
                "FOREIGN KEY(ID_ALIMENTO) REFERENCES ALIMENTO(ID_ALIMENTO) ON UPDATE CASCADE);" );

        db.execSQL("CREATE TABLE INFORMACAO_NUTRICIONAL(" +
                "ID_INFO_NUTRI  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOME           VARCHAR(100));" );

        db.execSQL("CREATE TABLE REL_ALIMENTO_INFO_NUTRI(" +
                "ID_ALIMENTO    INTEGER NOT NULL," +
                "ID_INFO_NUTRI  INTEGER NOT NULL," +
                "FOREIGN KEY(ID_ALIMENTO) REFERENCES ALIMENTO(ID_ALIMENTO)," +
                "FOREIGN KEY(ID_INFO_NUTRI) REFERENCES INFORMACAO_NUTRICIONAL(ID_INFO_NUTRI));");

        db.execSQL("CREATE TABLE GENERO_USUARIO(" +
                "ID_GENERO     INTEGER PRIMARY KEY AUTOINCREMENT," +
                "GENERO        VARCHAR(10));");

        //POPULANDO A TABELA CATEGORIA_EXERCICIO
        db.execSQL("INSERT INTO CATEGORIA_EXERCICIO(NOME) VALUES ('PEITO');");
        db.execSQL("INSERT INTO CATEGORIA_EXERCICIO(NOME) VALUES ('PERNA');");
        db.execSQL("INSERT INTO CATEGORIA_EXERCICIO(NOME) VALUES ('COSTAS');");
        db.execSQL("INSERT INTO CATEGORIA_EXERCICIO(NOME) VALUES ('BICEPS');");
        db.execSQL("INSERT INTO CATEGORIA_EXERCICIO(NOME) VALUES ('TRICEPS');");
        db.execSQL("INSERT INTO CATEGORIA_EXERCICIO(NOME) VALUES ('ABDOMEN');");
        db.execSQL("INSERT INTO CATEGORIA_EXERCICIO(NOME) VALUES ('OMBRO');");
        db.execSQL("INSERT INTO CATEGORIA_EXERCICIO(NOME) VALUES ('TRAPÉZIO');");

        //POPULANDO A TABELA GENERO_USUARIO
        db.execSQL("INSERT INTO GENERO_USUARIO(GENERO) VALUES ('FEMININO');");
        db.execSQL("INSERT INTO GENERO_USUARIO(GENERO) VALUES ('MASCULINO');");

        //POPULANDO A TABELA REL_TIPO_TREINO
        db.execSQL("INSERT INTO REL_TIPO_TREINO(NOME_TIPO_TREINO) VALUES ('DEFAULT');");
        db.execSQL("INSERT INTO REL_TIPO_TREINO(NOME_TIPO_TREINO) VALUES ('HIPERTROFIA');");
        db.execSQL("INSERT INTO REL_TIPO_TREINO(NOME_TIPO_TREINO) VALUES ('FORTALECIMENTO');");
        db.execSQL("INSERT INTO REL_TIPO_TREINO(NOME_TIPO_TREINO) VALUES ('RESISTENCIA');");

        //POPULANDO A TABELA EXERCICIO
        //EXERCICIOS PEITO
        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (1, 'SUPINO COM BARRA', 'O supino com barra em banco plano é o " +
                "exercício mais conhecido e realizado nos ginásios. Embora não existam músculos peitorais “superiores” e “inferiores”, o supino plano " +
                "parece recrutar mais as fibras intermédias (parte esternocostal)." +
                "Nota 1: Ao realizar o supino, tenha o cuidado de não utilizar um agarre demasiado afastado, pois tornará o exercícios menos eficiente e " +
                "aumentará as probabilidades de ocorrência de uma lesão, especialmente nas articulações dos ombros.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (1, 'SUPINO INCLINADO COM BARRA', 'Esta variação do supino plano coloca uma maior ênfase" +
                " na parte clavicular (superior) do grande peitoral. O supino inclinado com um ângulo de +45 graus, proporciona uma estimulação 69% mais elevada na " +
                "“parte superior do peitoral Nota: Quanto maior for o ângulo de inclinação, maior será o grau de participação dos músculos deltóides, especialmente da " +
                "parte anterior, no movimento.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (1, 'SUPINO DECLINADO COM BARRA', 'O supino declinado é a variação do supino " +
                "que recruta uma maior percentagem de fibras muscular, muito devido ao fato de também ser o tipo de supino em que é possível utilizar mais peso.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (1, 'SUPINO EM MÁQUINA', 'O supino em máquina é indicado especialmente para os " +
                "principiantes, ou indivíduos com algum tipo de limitação física que os impeça de treinar com barra e/ou halteres. É ideal para desenvolver " +
                "força e coordenação suficiente para se posteriormente para os pesos livres.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (1, 'SUPINO COM HALTERES', 'Esta variação do supino, é em quase tudo semelhante ao " +
                "supino com barra em banco plano, com a diferença de serem usados halteres em vez de uma barra, o que permite um arco de movimento mais natural.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (1, 'SUPINO INCLINADO COM HALTERES', 'Esta variação do supino inclinado, é em quase " +
                "tudo semelhante ao supino inclinado com barra em banco plano, com a diferença de serem usados halteres em vez de uma barra, o que permite um arco " +
                "de movimento mais natural.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (1, 'SUPINO DECLINADO COM HALTERES', 'Esta variação do supino inclinado, é em quase " +
                "tudo semelhante ao supino declinado com barra em banco plano, com a diferença de serem usados halteres em vez de uma barra, o que permite um arco " +
                "de movimento mais natural. Segundos análises EMG, de todos os exercícios de peitoral este é o exercício de peitoral que recruta a maior percentagem " +
                "de fibras musculares.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (1, 'CRUCIFIXO / ABERTURAS COM HALTERES', 'Existem dois tipos básicos de exercícios para o peitoral, " +
                "“empurrar” ao estilo do exercício supino e “abraças” ao estilo das aberturas que vemos nesta imagem: A imagem é auto-explicativa, este é um exercícios de isolamento " +
                "para o peitoral, é recomendável que o realize depois de algum tipo de movimento de supino.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (1, 'CRUCIFIXO / ABERTURAS INCLINADO COM HALTERES', 'Existem dois tipos básicos de " +
                "exercícios para o peitoral, “empurrar” ao estilo do exercício supino e “abraças” ao estilo das aberturas que vemos nesta imagem: A imagem é " +
                "auto-explicativa, este é um exercícios de isolamento para o peitoral, é recomendável que o realize depois de algum tipo de movimento de supino.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (1, 'CRUCIFIXO / ABERTURAS DECLINADO COM HALTERES', 'Esta variação do movimento " +
                "aberturas, trabalha em maior grau a parte clavicular do peitoral.', 0, 0, 0.0);");

        //EXERCICIOS PERNA
        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (2, 'LEG PRESS', 'Sente-se no aparelho de leg " +
                "press e coloque os pés com afastamento na plataforma igual à largura dos ombros. Lentamente, abaixe o peso até que os joelhos estejam com 90 " +
                "graus de flexão. Empurre o peso de volta à posição inicial; para isso, estenda as pernas.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (2, 'AVANÇO', 'Este é um dos mais antigos exercícios da musculação. Ele promove ao mesmo tempo uma flexão de joelho unilateral e uma extensão de quadril. Portanto, ele tem atuação forte sobre os músculos do quadríceps e tem como antagonistas, os músculos isquiotibiais. Ele pode ser realizado no início do treino ou no final, de acordo com a organização dos exercícios. Variações como a extensão de joelho no step, tem a mesma ação muscular na perna que vai à frente.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (2, 'LEG PREES 45º', 'Este exercício, que também tem uma flexão de joelho e uma extensão de quadril, é muito eficiente para trabalhar com os músculos do quadríceps e dos isquiotibiais (quando utilizada uma amplitude maior). Ao posicionar os pés mais acima no suporte, é possível ter uma sobrecarga mais elevada nos músculos isquiotibiais. Ele é muito interessante em seu treino, pela fácil utilização e pela possibilidade de usar cargas mais elevadas.\n" +
                "\n" +
                "É um ótimo complemento para exercícios como agachamento e avanço (quadríceps) ou o stiff (isquiotibiais).\n" +
                "\n" +
                "Pelo fato do leg press 45 ser guiado, temos um movimento mais “fácil”, desde que tenhamos uma execução correta. Para isso, você deve manter as costas apoiadas totalmente no banco, o tempo todo. Se você não consegue evitar “tirar” o apoio da lombar, reduza a amplitude do movimento.\n" +
                "\n" +
                "Além disso, muitas pessoas erram neste movimento, ao usar uma carga elevada, no intuito de “demonstrar força”. O LegPress 45 é muito efetivo, desde que seja usado da forma correta, com carga adequada e muita qualidade no movimento e nas variáveis.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (2, 'AGACHAMENTO', 'O exercício mais completo para qualquer treino de pernas. Engrossar as coxas sem a presença do agachamento em seu treino é uma tarefa muito mais difícil. Ele produz uma flexão de joelho e extensão de quadril e quando feito em afundo, atua muito fortemente nos músculos dos isquiotibiais e glúteos.\n" +
                "\n" +
                "É um exercício fundamental para qualquer treino com o objetivo de engrossar as coxas. É muito importante usar ele de maneira correta, com uma boa execução e sem abusar das cargas.\n" +
                "\n" +
                "Assim como nos exercícios listados acima, por ser um movimento multiarticular, o agachamento é muito importante para engrossar as coxas. Além de ser altamente funcional e envolver inúmeros grupamentos musculares, entre sinergistas, agonistas e antagonistas. Mas é lógico que ele precisa ser feito com qualidade de movimento, com a amplitude correta e com uma quilagem que traga os estímulos esperados.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (2, 'HACK MACHINE', 'O hackmachine é uma máquina que reproduz o movimento do agachamento convencional, porém, com as costas apoiadas e feito de maneira guiada. É interessante usar ele como complemento do treino com pesos livres, mas não como principal exercício.\n" +
                "\n" +
                "A grande vantagem do hack machine é que ele tem um movimento guiado e é mais fácil de ser executado em momentos em que você já desgastou seus músculos.\n" +
                "\n" +
                "Além disso, ele permite um controle de variáveis muito interessante. ', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (2, 'AGACHAMENTO NO SMITH', 'Outro exercício com o mesmo movimento do agachamento, mas com a barra guiada. É mais intenso que o hack, mas também deve ser usado como complemento de um treino com pesos livres.\n" +
                "\n" +
                "Assim como no caso do hack machine, o agachamento no Smith pode ser usado de uma forma mais intensa como complemento de outros movimentos ou em determinadas técnicas de treino.\n" +
                "\n" +
                "Tudo vai depender da forma como seu treino de pernas é realizado, mas no geral, este é um exercício muito interessante para os mais variados perfis. \n" +
                "\n', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (2, 'STIFF', 'Um dos exercícios para isquiotibiais com maior atividade muscular. Ele pode ser executado com as pernas estendidas, para a maior utilização dos isquiotibiais, ou ainda, com os joelhos levemente flexionados, para a maior utilização dos glúteos. Pessoas com mais flexibilidade, podem fazer ele em cima de um step, para aumentar a amplitude de movimento.\n" +
                "\n" +
                "Dependendo da forma como o stiff é realizado, podemos ter um enfoque maior ou menor nos glúteos e nos isquiotibiais. Já mencionamos sobre isso neste artigo (Stiff, como utilizá-lo corretamente em seu treino (5 dicas importantes)).\n" +
                "\n" +
                "Além disso, por ser um movimento que envolve uma estabilização da coluna lombar muito forte, é muito importante que ele seja feito de forma progressiva e com a devida qualidade. ', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (2, 'MESA FLEXORA', 'Um exercício guiado para os músculos dos isquiotibiais, é bastante interessante quando usado como complemento de outros exercícios, como o stiff. Não deve ser usado como único exercício para esta finalidade, a não ser em casos especiais, onde não podemos usar outros movimentos para o trabalho de isquiotibiais. \n" +
                "\n', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (2, 'AGACHAMENTO SUMÔ', 'Para pessoas mais experientes, o afastamento das pernas (não em excesso), durante o agachamento, faz com os músculos adutores sejam trabalhados de maneira mais eficiente. Já a atuação sobre o quadríceps é igual ao agachamento convencional. Pessoas com problemas no joelho devem ter maior cuidado com este exercício.\n" +
                "\n" +
                "Mas ele é uma variação interessante, desde que seja usado no contexto correto e com uma execução bem feita. Caso contrário ele pode vir a trazer problemas articulares.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (2, 'CADEIRA ADUTORA', 'Focada nos músculos adutores (parte de dentro das coxas), este é um importante exercício, não apenas para engrossar as coxas, mas também para a estabilização dos movimentos e prevenção de lesões.\n" +
                "\n', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (2, 'CADEIRA ABDUTORA', 'Focada nos músculos da parte lateral  da coxa, tem grande participação no treino para engrossar as coxas. Além disso, tem um papel estabilizador muito importante.\n" +
                "\n" +
                "Existem mais uma centena de exercícios pernas e coxas e variações destes citados acima, mas creio que com estes, quando bem escolhidos e bem aplicados, você terá um treino eficiente para engrossar as coxas. Como o objetivo é a hipertrofia, o tempo entre as séries, a velocidade de execução, bem como o número destas precisam ser estabelecidos para este objetivo.', 0, 0, 0.0);");

        //EXERCICIOS COSTAS
        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (3, 'BARRA FIXA', 'Trata-se de um exercício que promove bastante força à parte superior do corpo. Além de trabalhar as costas, ele também mexe com outras regiões musculares como os braços e os ombros.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (3, 'REMADA CURVADA', 'É um exercício que aciona boa parte da musculatura das costas. No entanto, é fundamental certificar-se de manter a forma correta durante a execução e de utilizar cargas apropriadas para não correr o risco de se machucar. Daí a importância de ter a ajuda de um bom instrutor físico para acompanhar o treino.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (3, 'BARRA FIXA COM PEGADA LARGA', 'Este é um dos mais clássicos exercícios para costas. A barra fixa com pegada larga coloca ênfase na parte superior do músculo latíssimo do dorso. Durante o movimento é importante ter atenção quanto à forma do corpo; na posição inicial, antes de iniciar a impulsão, as escápulas devem estar recolhidas.\n" +
                "Este exercício composto trabalha força, possui nível de dificuldade intermediário e além de acionar o latíssimo do dorso, trabalha os bíceps e a parte do meio das costas, de maneira secundária.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (3, 'PULLOVER COM ALTERE', 'Temos aqui outro exercício que trabalha o latíssimo do dorso, os músculos intercostais (localizados no tórax) e o serrátil anterior (que fica na caixa torácica), além de acionar os peitorais e os tríceps de maneira secundária.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (3, 'REMADA BAIXA SENTADA', 'A remada sentada é realizada com o auxílio de cabos e trabalha a parte média e superior das costas, regiões que não costumam ser atingidas com facilidade por outros exercícios para costas.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (3, 'REMADA EM PÉ NA BARRA T', 'Durante a remada na barra T é preciso manter as pernas bloqueadas em uma posição de ângulo curvado. É possível escolher um tipo de pegada para o exercício e tal decisão altera o músculo que será mais trabalhado.\n" +
                "Por exemplo, uma pegada neutra trabalha melhor a região média das costas, enquanto uma pegada mais ampla aciona mais o latíssimo do dorso.\n" +
                "Trata-se de uma das remadas mais fáceis de serem executadas, ainda que seu nível de dificuldade seja intermediário.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (3, 'REMADA CURVADA COM PEGADA INVERTIDA', 'Este é um dos exercícios para costas que também exigem bastante dos bíceps.\n" +
                "Para fazer a remada curvada com pegada invertida é necessário posicionar-se em pé, com as pernas afastadas e segurar uma barra de pesos reta com as mãos. A distância das mãos deve ser equivalente à largura dos ombros.\n" +
                "Como o próprio nome do exercício já indica, a pegada deve ser invertida, ou seja, com as palmas das mãos viradas para a frente. Já os joelhos devem estar levemente flexionados. \n" +
                "Na sequência, alinhar a coluna e encaixar os ombros para trás. Então, inclinar o tronco para a frente, jogando o quadril para trás e com os cotovelos estendidos, até que a barra de pesos fique na altura das coxas.\n" +
                "O próximo passo é puxar a barra até a cintura ao mesmo tempo em que flexiona os cotovelos. Também aconselha-se a contrair o bíceps. Por fim, segurar a contração por um momento e retornar ao posicionamento original.\n" +
                "Importante: as pernas e o tronco devem ser mantidos imóveis ao longo de todo o exercício.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (3, 'REMADA NO SMITH COM PEGADA INVERTIDA', 'O exercício foca na parte baixa do latíssimo do dorso, trazendo a vantagem do Smith de poder puxar o máximo de peso que conseguir, já que não é necessário se preocupar com o equilíbrio. O site Body Building recomenda inclinar-se em um ângulo de 45º, ficando próximo à barra, e esperar certa ajuda dos joelhos e quadris na hora de executar as séries mais pesadas.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (3, 'PULL-DOWN DE PEGADA FECHADA', 'Trata-se de um exercício que aciona o latíssimo do dorso. A pegada fechada permite que a amplitude do movimento seja maior e que o tempo sob tensão para o músculo latíssimo do dorso também seja maior, o que é bom para a construção muscular.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (3, 'LAT PULL-DOWN', 'Trata-se de um exercício que trabalha o latíssimo do dorso, a região média das costas, os bíceps e os ombros. Seu nível de dificuldade é iniciante e ele é classificado como um movimento de fortalecimento.', 0, 0, 0.0);");

        //EXERCICIOS BICEPS
        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (4, 'ROSCA DIRETA COM BARRA', 'O primeiro item da nossa lista de exercícios para bíceps é a rosca direta com barra, que tem como objetivo o aumento de volume desta região.\n" +
                "\n" +
                "Para fazer a rosca direta com barra, você deve segurar a barra com seus braços estendidos e deixar as mãos afastadas na mesma distância dos ombros, com as palmas das mãos voltadas para cima. O próximo passo é levantar a barra até o nível dos ombros, flexionando os cotovelos.\n" +
                "Na parte final, deve-se devolver a barra à sua posição inicial, retornando os braços para a posição estendida. Se usar uma pegada aberta, você concentrará seu esforço na parte interna do bíceps. Já se utilizar a pegada fechada, o esforço será concentrado em sua parte externa.\n" +
                "Durante a execução do exercício, o corpo deve estar na posição ereta, com a coluna vertebral reta. A inclinação do torso pode ser usada como tática para o movimento. Fazer uma inclinação leve para a frente torna a fase inicial da flexão de braços mais fácil e outra inclinação leve, só que para trás, auxilia na fase final da repetição.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (4, ' BARRA FIXA', 'Esse é um exercício clássico para costas, mas também pode ser um dos exercícios para bíceps por trabalhar bastante este músculo.\n" +
                "Segundo o proprietário de um centro de treinamento em Massachusetts, nos Estados Unidos, Tony Gentilcore, trata-se de um exercício que promove bastante força à parte superior do corpo. Além de trabalhar o bíceps, que é usado para puxar o peso do corpo, ele também utiliza outros músculos superiores, com os ombros e as costas.\n" +
                "Para fazer o exercício, você deve segurar a barra com as mãos, a uma distância equivalente à largura dos ombros, e pendurar-se na barra, conforme exibido na figura acima, com os pés cruzados. Então, aperte as escápulas dos ombros para baixo e para trás, dobre os cotovelos e puxe a parte de cima do peito em direção à barra.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (4, 'ROSCA CONCENTRADA', 'Quando a pessoa senta para executar a rosca concentrada, a ênfase do exercício acontece diretamente no bíceps. Além disso, se o movimento for realizado adequadamente, ele não será feito com ajuda de nenhum outro músculo que não o bíceps.\n" +
                "Isso faz com que a rosca concentrada seja um dos melhores exercícios para bíceps para finalizar o treino, onde se deseja cansar por completo o músculo em questão.\n" +
                "Para iniciar o exercício é necessário sentar-se na ponta de um banco com as pernas afastadas e segurar um halter em uma das mãos. Os joelhos devem estar flexionados e os pés firmes no chão. O cotovelo da mão que segura o peso deve ser apoiado na parte interna da coxa, que fica perto do joelho, e estar estendido. A pegada é com a mão virada em direção à outra coxa.\n" +
                "Enquanto isso, a mão livre deve apoiar na outra coxa para ajudar na estabilidade da posição. Depois, eleve o halter, fazendo o movimento de flexão do cotovelo, até que a carga se aproxime do ombro. Toda a força deve ficar concentrada no bíceps.\n" +
                "Segure a contração por um tempinho, desça o haltere de modo controlado para retornar à posição original. É importante não mexer o corpo durante o exercício: somente o antebraço é que deve se movimentar.\n" +
                "Depois que fizer as repetições em um braço, troque e realize a rosca concentrada com o outro.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (4, 'ROSCA DIRETA COM HALTERES', 'Temos aqui um exercício que trabalha principalmente o bíceps, porém ainda atinge o antebraço. Seu foco é o fortalecimento e seu nível de dificuldade é iniciante.\n" +
                "O exercício começa com o praticante em pé com um halter em cada mão na altura dos braços. Os cotovelos devem estar próximos ao tronco e as palmas das mãos direcionadas para a frente.\n" +
                "Então, com os antebraços imóveis, exalar e erguer os pesos ao mesmo tempo que contrai os bíceps. Isso deve ser feito até que os bíceps estejam completamente contraídos e os halteres se encontrem na altura dos ombros.\n" +
                "O próximo passo é segurar a posição contraída por um instante enquanto comprime bem os ombros. Após isso, inspirar o ar e retornar lentamente ao posicionamento original.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (4, 'ROSCA NA BARRA EZ', 'Temos aqui mais uma modalidade de exercícios para bíceps. Desta vez, a rosca é feita com a barra EZ, de nível iniciante e trabalha o fortalecimento da região muscular.\n" +
                "O exercício começa na posição em pé, segurando a barra na parte exterior e larga do cabo, com as palmas das mãos direcionadas para fora, apontando para frente e levemente inclinadas para dentro devido ao formato da barra. Os cotovelos devem estar próximos ao tronco.\n" +
                "Com os antebraços imóveis, exalar. Depois, flexionar os cotovelos e levar a barra para cima. Os pesos devem ser erguidos até os bíceps estarem totalmente contraídos e a barra atingir a altura dos ombros.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (4, 'ROSCA EM PÉ COM BARRA E PEGADA AMPLA', 'Segurar a barra com uma pegada maior do que a usual faz com que a cabeça curta do bíceps seja mais envolvida no movimento. É importante tomar cuidado para não se inclinar para trás durante a execução do exercício.\n" +
                "A rosca em pé com barra e pegada ampla é considerada um exercício de força, que possui nível iniciante.\n" +
                "Para executá-la é necessário ficar de pé, com o tronco reto. O próximo passo é segurar na parte exterior e larga do cabo de uma barra de pesos, com as palmas das mãos direcionadas para fora, em uma distância maior que a altura dom ombros.\n" +
                "Os cotovelos devem estar próximos do tronco. Em seguida, erguer os pesos enquanto contrai os bíceps e exala o ar. Continuar o movimento até que os bíceps estejam totalmente contraídos e a barra se encontre no nível dos ombros.\n" +
                "Então, segurar a posição por um segundo e comprimir bem os bíceps. Finalmente, retornar lentamente à posição original enquanto inspira o ar.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (4, 'ROSCA NO CABO', 'A rosca com cabo é realizada com uma barra curta que fica presa a uma polia baixa. A pegada é feita com as palmas das mãos apontadas para baixo e a posição inicial do exercício é com os braços estendidos, em pé, de maneira ereta e com a coluna vertebral reta.\n" +
                "Então, é feito o movimento de levantamento, com a flexão dos cotovelos. Depois, há o retorno ao posicionamento inicial, em que os braços voltam a ficar estendidos.\n" +
                "Assim como acontece com a rosca direta com barra, se usar uma pegada aberta, você concentrará seu esforço na parte interna do bíceps. Já se utilizar a pegada fechada, o esforço será concentrado em sua parte externa.\n" +
                "Entretanto, há uma diferença em relação à resistência da rosca com cabo e de outros tipos exercícios para bíceps de rosca, feitos com halter ou barra. Nessas últimas, a resistência varia durante a elevação, enquanto no movimento realizado com o cabo a resistência é uniforme o tempo todo.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (4, 'ROSCA INCLINADA COM HALTERES', 'Além dos halteres, o exercício também requer o uso de um banco, onde o praticante deve deitar suas costas, como mostra a imagem acima. O movimento exige mais da parte externa do bíceps braquial.\n" +
                "Para fazer o exercício é preciso deitar as costas em um banco de inclinação de 45º. A posição inicial ainda prevê os joelhos dobrados, os pés no chão e os braços estendidos com um haltere em cada mão. As palmas das mãos devem estar voltadas para a frente.\n" +
                "Uma vez no banco, o praticante deve dobrar os cotovelos e elevar os halteres o mais perto que conseguir de seus ombros, sem movimentar o antebraço. Após isso, é o momento de fazer uma pausa e abaixar os pesos, retornando devagar à posição inicial, tratando de deixar os braços bem endireitados.\n', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (4, 'ROSCA MARTELO INCLINADA', 'Deitar-se no banco na posição inclinada faz com que o alongamento da cabeça longa do bíceps seja maior, ao mesmo tempo em que a pegada neutra se concentra no músculo braquiorradial e no músculo braquial.\n" +
                "No entanto, o movimento da rosca martelo faz com que parte da tensão por estar na posição inclinada seja retirada da cabeça longa do bíceps. \n" +
                "A rosca martelo inclinada começa com o praticante deitado no banco inclinado e segurando um halter em cada uma de suas mãos. Os braços devem estar estendidos e suspensos ao lado do corpo e os pés devem estar separados e na mesma linha, como a imagem acima mostra.\n" +
                "O movimento começa com a flexão dos cotovelos, com os halteres em pé, como nas fotos. Neste momento, os antebraços devem permanecer imóveis. Quando chegar à parte alta do exercício, fazer uma pequena pausa e, então, voltar lentamente à posição original.', 0, 0, 0.0);");

        //EXERCICIOS TRICEPS
        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (5, 'EXTENSÃO DE TRICEPS DEITADO', 'O primeiro exercício da nossa lista de exercícios para tríceps trabalha tanto a cabeça lateral quanto a cabeça longa do músculo e é classificado como um dos melhores movimentos para ativar o tríceps de maneira geral.\n" +
                "\n" +
                "A extensão de tríceps deitado é um exercício de força com nível de dificuldade iniciante, que também trabalha a região dos antebraços. Ela é executada com o auxílio de uma barra EZ.\n" +
                "Como fazer: deitar-se de costas em um banco reto e segurar a barra com uma pegada fechada, as palmas das mãos apontando para fora e os braços estendidos e perpendiculares ao solo, como na foto acima. A partir dessa posição inicial, abaixar a barra, flexionando os cotovelos e mantendo os antebraços fixos. \n" +
                "Inalar durante a descida da barra e pausar quando ela se encontrar diretamente acima da testa. Então, levantar a barra e retornar ao posicionamento original, ao mesmo tempo em que exala o ar e estende os cotovelos.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (5, 'Supino no banco reto com pegada fechada', 'Este exercício composto de força trabalha primordialmente os tríceps, porém, também aciona o peito e os ombros. Um estudo já mostrou que quando o exercício é feito em um banco reto com uma pegada menor, há uma ativação maior na cabeça longa do tríceps do que no banco inclinado.\n" +
                "A pegada fechada deve ser de uma distância de 20 a 25 cm entre as mãos para trabalhar bem os tríceps. Uma pegada menor que essa não coloca mais estresse nos braços, porém, traz um aumento de tensão nos pulsos, que não é o objetivo aqui.\n" +
                "Para os iniciantes no exercício, recomenda-se ter o acompanhamento de alguém durante sua execução. Também é importante ser cauteloso quanto à carga escolhida e não forçar além de seus limites, além de prestar atenção para que a barra alcance a região média do peito durante a descida. Ela não deve parar em nenhum outro lugar que não seja esse.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (5, 'Mergulho no banco', 'Esta versão de exercícios para tríceps usa o peso do corpo e é executada com o auxílio de dois bancos retos. Também traz bastante ativação ao músculo do tríceps. Ao adicionar carga ao exercício, colocando um peso no colo, ele torna-se ainda mais eficiente.\n" +
                "No entanto, recomenda-se que a primeira vez em que for fazer o mergulho no banco, o peso não seja utilizado, para que você se acostume com os movimentos. Então, quando ele se tornar algo fácil de executar, você pode começar a adicionar a carga.\n" +
                "O movimento é composto e além de acionar os tríceps, trabalha o peito e os ombros. Seu nível de dificuldade é intermediário.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (5, 'Tríceps no banco', 'Este tipo de exercícios para tríceps possui nível de dificuldade iniciante. Ele também aciona outros músculos como os deltoides, glúteos, posteriores da coxa, abdominais e quadris.\n" +
                "Durante a execução do tríceps no banco, recomenda-se manter o corpo reto e evitar o relaxamento excessivo dos quadris. Também é importante manter os cotovelos presos durante o exercício e evitar que eles abram para os lados.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (5, 'Extensão de halteres sob a cabeça', 'Quando a extensão de halteres sob a cabeça é feita, com os braços erguidos acima da cabeça, a região da cabeça longa do tríceps é acionada. Este exercício de força possui um nível de dificuldade iniciante e além de atingir os tríceps, também trabalha os deltoides e o trapézio.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (5, 'Mergulho com peso nas paralelas', 'Este item da nossa lista de exercícios para tríceps trabalha também os deltoides e possui um nível de dificuldade intermediário. Para acrescentar a carga ao movimento, dá para adicionar caneleiras com peso nos tornozelos ou amarrar um cinto com um prato de peso na cintura.\n" +
                "Tenha atenção em relação à quantidade de carga utilizada para que seja de acordo com suas condições e não vá além de seus limites, forçando o corpo. Outra dica relevante é manter o corpo o mais vertical que conseguir durante o exercício para focar o trabalho nos tríceps. Se você inclinar para a frente, acionará o peito.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (5, 'Mergulho de tríceps na máquina', 'Trata-se de um exercício composto que trabalha os tríceps, além de atingir os ombros e o peito. A carga deve ser escolhida de acordo com seus objetivos: se o foco do seu treino for força, a indicação é trabalhar com mais peso do que se a meta for a construção muscular. Sem esquecer de selecionar a carga conforme suas condições e não forçar-se para não se machucar.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (5, 'Extensão no cabo sob a cabeça com corda', 'Temos outro exercício de nível de dificuldade intermediário que aciona a cabeça longa dos tríceps. Seu foco é a força e ele é executado no aparelho de cabo com uma corda. Durante todo o exercício, é importante que os cotovelos permaneçam fixos.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (5, 'Tríceps na polia alta com barra reta', 'Temos aqui um exercício que trabalha a cabeça lateral do tríceps melhor do que outros movimentos como a extensão de tríceps deitado, por exemplo. O exercício trabalha os tríceps de maneira isolado.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (5, 'Tríceps na polia alta com corda', 'O último item da nossa lista de exercícios para tríceps possui um nível iniciante de dificuldade. Ele age de maneira isolada e recebeu a nota 9 no site Body Building – em uma escala que vai de zero a 10 – tendo sido classificado como excelente.', 0, 0, 0.0);");

        //EXERCICIOS ABDOMEN
        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (6, 'Prancha lateral', 'Apoiada de lado, posicione o cotovelo no chão, alinhado com os ombros e com os calcanhares, mantendo o corpo em uma posição ereta.\n" +
                "Eleve o braço em uma abdução de ombro, forçando a contração muscular de forma geral.\n" +
                "Se for iniciante, apoie os joelhos em flexão no chão, mantendo assim, cotovelo, ombro e joelhos alinhados.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (6, 'Abdominal em V', 'Deitada no chão, de barriga para cima, levante um pouco as pernas.\n" +
                "Com os braços esticados para frente, levante o tronco do chão, como mostra a imagem.\n" +
                "Com cuidado para não forçar o pescoço.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (6, 'Abdominal infra', 'Com as costas e cabeça apoiadas no chão, coloque as mãos na lateral ou abaixo dos glúteos (para dar um maior apoio lombar).\n" +
                "Eleve as pernas com os joelhos estendidos, levemente e abaixe lentamente, mantendo a lombar no chão e abdômen sempre contraído.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (6, 'Prancha com bola', 'Neste exercício, deve-se ficar na posição que mostra a imagem e então dobrar os joelhos ao mesmo tempo,\n" +
                "trazendo a bola para mais perto do tronco, sem movimentar os braços.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (6, 'Abdominal oblíquo', 'Com as costas apoiadas no chão, flexione o quadril e os joelhos e cruze a perna esquerda sobre a direita.\n" +
                "Coloque a mão direita abaixo do pescoço e faça uma flexão de tronco cruzado, levando o cotovelo direito próximo ao joelho esquerdo.\n" +
                "Mantenha sempre o abdômen contraído.\n" +
                "Repita o mesmo movimento para o outro lado.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (6, 'Abdominal com perna elevada', 'Deitada no chão de barriga para cima, levante as pernas esticadas.\n" +
                "Com os braços esticados para frente, levante o tronco do chão, como mostra a imagem.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (6, 'Prancha Ventral', 'De bruços, cotovelos e joelhos apoiados no chão (A).\n" +
                "Tire os joelhos do chão e mantenha o corpo reto,\n" +
                "deixando apenas uma leve curvatura na coluna e contraindo o abdômen (B).', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (6, 'Abdominal para a parte inferior da barriga', 'Deite de barriga para cima, com as mãos embaixo do bumbum.\n" +
                "Estique as pernas a 90 graus.\n" +
                "Desça as pernas devagar, até quase encostar os pés no chão.\n" +
                "Suba a perna novamente, repetindo o movimento.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (6, 'Abdominal clássico', 'Deite sobre um colchonete ou tapete.\n" +
                "Pés apoiados no chão, pernas flexionadas.\n" +
                "Mãos ao lado das orelhas.\n" +
                "Subo o tronco em direção aos joelhos.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (6, 'Abdominal com elevação do quadril na bola', 'Deitado no chão, de barriga para cima,\n" +
                "coloque a planta dos pés numa bola de Pilates e eleve o tronco,\n" +
                "sem deixar a bola sair do lugar.', 0, 0, 0.0);");

        //EXERCICIOS OMBRO
        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (7, 'Shoulder Press com Halteres Fixos', 'Modo de execução: Sente-se num banco e coloque os halteres de forma com que o seu braço e antebraço formem um ângulo de 90 graus. As palmas das mãos devem estar voltadas para a frente. Levante os halteres até ficar com os braços esticados. Retorne à posição inicial. As costas devem estar sempre eretas', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (7, 'Levantamento Lateral com Halteres Fixos', 'Modo de execução: De pé, segure os halteres com os braços estendidos, um de cada lado. Eleve os braços para fora até que os halteres atinjam o nível dos ombros. Regresse lentamente à posição inicial.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (7, 'Levantamento Frontal com Halteres ou Barra', 'Modo de execução: Segure um haltere de barra com as palmas das mãos voltadas para dentro e à largura dos ombros. Os braços devem estar completamente esticados. Levante o haltere para a frente e para cima até o nível dos ombros. Regresse lentamente à posição inicial.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (7, 'Remada Alta em pé para Trapézio e Ombros', 'Modo de execução: Segure a barra com os braços estendidos, afastados na largura dos ombros. A pegada deve ser com o dorso das mãos voltado para cima. Puxe a barra para cima, verticalmente. Levante os cotovelos até a altura do ombro.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (7, 'Levantamento de Halteres fixos com Inclinação para frente', 'Modo de execução: Segure os dois halteres com os braços estendidos, incline o corpo para a frente usando a cintura e mantenha as costas retas e a cabeça levantada. Com as palmas das mãos voltadas para dentro, levante os halteres para cima até ao nível das orelhas, mantendo os cotovelos ligeiramente dobrados. Abaixe os halteres lentamente até à posição inicial.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (7, 'Crucifixo Invertido no Aparelho', 'Modo de execução: Sente no aparelho com o peito contra o encosto do banco. Com os braços estendidos ao nível do ombro, pegue os puxadores do aparelho. Puxe-os para trás em um arco.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (7, 'Rotação Interna', 'Modo de execução: Posicione-se de lado a uma polia de cabo, com ajuste à altura da cintura. Segure o pegador com a mão ‘de dentro’, com o polegar apontando para cima como demonstrado na figura abaixo. Mantenha o cotovelo firme, movimente o pegador para dentro, passando pela frente do corpo.  Como demonstrado abaixo na figura (A).', 0, 0, 0.0);");

        //EXERCICIOS TRAPÉZIO
        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (8, 'Encolhimento de ombros com halteres', 'Para fazer o encolhimento de ombros com halteres é necessário posicionar-se de pé, com os pés distantes a uma altura equivalente à largura dos ombros e segurar um haltere em casa mão, com as palmas viradas para o corpo.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (8, 'Encolhimento de ombros com barra pela frente', 'Continuando a nossa série de exercícios para trapézio, temos o encolhimento de ombros com barra pela frente. Para começar, uma barra deve estar apoiada em um suporte ou no chão. Em pé, o praticante é orientado a segurar a barra com as duas mãos, com as palmas viradas para baixo. A distância entre as mãos deve ser similar à largura dos ombros.\n" +
                "O peso deve estar suspenso um pouco abaixo da linha da cintura, os cotovelos estendidos e o ombros para trás. Com o tronco reto ou levemente inclinado para a frente e as pernas semiflexionadas, temos a posição inicial da série.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (8, 'Encolhimento de ombros com barra por trás', 'Para começar a série, é preciso apoiar uma barra no chão ou em suporte. Em pé, posicionar-se em frente à barra e segurá-la atrás do corpo, com as palmas voltadas para trás. A distância entre as mãos deve ser igual à largura dos ombros.\n" +
                "Suspender o peso um pouco abaixo do bumbum e manter os cotovelos estendidos e os ombros encaixados para trás. Para completar a posição inicial, as pernas devem estar semiflexionadas e o tronco inclinado levemente para frente.', 0, 0, 0.0);");

        db.execSQL("INSERT INTO EXERCICIO(ID_CATEGORIA, NOME, DESCRICAO, QTD_SERIES, QTD_REPETICOES, PESO) VALUES (8, 'Remada alta com barra', 'Séries de remada são importantes exercícios para trapézio porque além de trabalhar a parte de baixo e do meio do músculo, colaboram para que a região da omoplata – osso que está localizado nos ombros – permaneça estável enquanto um peso é levantado. Com isso, os ombros não serão movimentados na hora de fazer exercícios para o peito e braços, algo que pode limitar a força. Para fazê-lo é preciso ficar de pé, deixar os pés afastados na largura dos ombros e segurar uma barra de pesos com as palmas voltadas para cima. As mãos distantes também na largura dos ombros, podendo ter variações com as mãos mais próximas (com os polegares se encontrando) ou em uma pegada bem aberta. Os joelhos devem estar semiflexionados e os braços estendidos.\n" +
                "Depois, é hora de fazer o movimento de subida com a barra: ela deve ser elevada até a altura da cabeça e os cotovelos levados bem para cima. Uma indicação relevante é tomar cuidado para não mandar os cotovelos para trás, eles devem ser erguidos lateralmente.', 0, 0, 0.0);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE USUARIO;");
        db.execSQL("DROP TABLE TREINO;");
        db.execSQL("DROP TABLE REL_ALIMENTO_INFO_NUTRI;");
        db.execSQL("DROP TABLE INFORMACAO_NUTRICIONAL;");
        db.execSQL("DROP TABLE REL_DIETA_ALIMENTO;");
        db.execSQL("DROP TABLE ALIMENTO;");
        db.execSQL("DROP TABLE DIETA;");
        db.execSQL("DROP TABLE REL_TREINO_EXERCICIO;");
        db.execSQL("DROP TABLE CATEGORIA_EXERCICIO;");
        db.execSQL("DROP TABLE EXERCICIO;");
        db.execSQL("DROP TABLE GENERO_USUARIO;");
        db.execSQL("DROP TABLE REL_TIPO_TREINO;");

        onCreate(db);
    }
}
