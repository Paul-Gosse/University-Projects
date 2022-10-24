package rendu;

import java.awt.*;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;





public class Menu3 extends Application {

    private GameMenu gameMenu;
    private double largeur = (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
    private double hauteur = (Toolkit.getDefaultToolkit().getScreenSize().getHeight()-70);


    @Override
    //Lancement de la scène avec tout ce qu'elle contient.
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("N-Body Problem");
        Pane root = new Pane();
        root.setPrefSize(largeur, hauteur);

        InputStream is = Files.newInputStream(Paths.get("systeme.jpg"));
        Image img = new Image(is);
        is.close();

        ImageView imgView = new ImageView(img);

        gameMenu = new GameMenu();


        root.getChildren().addAll(imgView, gameMenu);


        Scene scene = new Scene(root);
        gameMenu.setVisible(true);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Création de tous les éléments nécessaires aux menus et a leurs transitions
    private class GameMenu extends Parent {
        public GameMenu() {
            VBox menu0 = new VBox(10);
            VBox menu1 = new VBox(10);
            VBox menu2 = new VBox(10);
            VBox menuTerre= new VBox(10);
            VBox menuMars= new VBox(10);            //création des differents menus
            VBox menuUranus= new VBox(10);
            VBox menuVenus= new VBox(10);
            VBox menuSaturne= new VBox(10);
            VBox menuJupiter= new VBox(10);
            VBox menuMercure= new VBox(10);
            VBox menuNeptune= new VBox(10);

            menu0.setTranslateX(largeur/2.5);
            menu0.setTranslateY(hauteur/2.5);
            //Placement de ces menus au centre de la fenetre.
            menu1.setTranslateX(largeur/2.5);
            menu1.setTranslateY(hauteur/3.5);

            menu2.setTranslateX(largeur / 2.5);
            menu2.setTranslateY(hauteur / 2.5);

            final int offset = 800; // Vitesse d'apparition des menus.

            menu1.setTranslateX(offset);
            menu2.setTranslateX(offset);
            menuTerre.setTranslateX(offset);
            menuMars.setTranslateX(offset);
            menuUranus.setTranslateX(offset);
            menuVenus.setTranslateX(offset);    //Ajout de la vitesse d'apparition des menus après la transition.
            menuSaturne.setTranslateX(offset);
            menuJupiter.setTranslateX(offset);
            menuMercure.setTranslateX(offset);
            menuNeptune.setTranslateX(offset);


            // -------------------------------------------------- Menu Principal ---------------------------------------------------

            //Evènement pour lancer la simulation.
            MenuButton btnResume = new MenuButton("LANCER LA SIMULATION");
            btnResume.setOnMouseClicked(event -> {
                new LanceurSystemeSolaire();
            });

            MenuButton btnchore = new MenuButton("CHOIX DES CHOREGRAPHIES");
            btnchore.setOnMouseClicked(event -> {
                getChildren().add(menu2);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu2);
                tt1.setToX(menu0.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu0);
                });
            });

            MenuButton btnOptions = new MenuButton("PLANETES DISPONIBLES");
            btnOptions.setOnMouseClicked(event -> {
                getChildren().add(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);
                //Transition pour accéder aux planetes disponibles.
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menu0.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu0);
                });
            });


            MenuButton btnExit = new MenuButton("QUITTER");
            btnExit.setOnMouseClicked(event -> {
                System.exit(0);
            });


            // -------------------------------------------------- Menu des chorégraphies  ---------------------------------------------------
            MenuButton btnBack9 = new MenuButton("RETOUR");
            btnBack9.setOnMouseClicked(event -> {
                getChildren().add(menu0);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu2);
                tt.setToX(menu2.getTranslateX() - offset);
                //Transition pour accéder aux choregra
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                tt1.setToX(menu2.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu2);
                });
            });




            MenuButton btnchore2 = new MenuButton("FIGURE EIGHT");
            btnchore2.setOnMouseClicked(event -> {
                new LanceurChore();
            });

            MenuButton btnchore1 = new MenuButton("FIGURE MOTH 1");
            btnchore1.setOnMouseClicked(event -> {
                new LanceurChore1();
            });

            // -------------------------------------------------- Menu des planètes et transition vers infos  ---------------------------------------------------

            MenuButton btnBack = new MenuButton("RETOUR");
            btnBack.setOnMouseClicked(event -> {
                getChildren().add(menu0);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() - offset);
                                                                                                     //Transition pour accéder menu precedent.
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                tt1.setToX(menu1.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu1);
                });
            });

            MenuButton btnTerre = new MenuButton("LA TERRE");
            btnTerre.setOnMouseClicked(event -> {
                getChildren().add(menuTerre);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() - offset);
                                                                                                    //Transition pour accéder aux informations sur La Terre.
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuTerre);
                tt1.setToX(menu1.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu1);
                });
            });

            MenuButton btnMars = new MenuButton("MARS");
            btnMars.setOnMouseClicked(event -> {
                getChildren().add(menuMars);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() - offset);
                                                                                                    //Transition pour accéder aux informations sur Mars.
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuMars);
                tt1.setToX(menu1.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu1);
                });
            });

            MenuButton btnUranus = new MenuButton("URANUS");
            btnUranus.setOnMouseClicked(event -> {
                getChildren().add(menuUranus);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() - offset);
                                                                                                    //Transition pour accéder aux informations sur Uranus.
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuUranus);
                tt1.setToX(menu1.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu1);
                });
            });

            MenuButton btnVenus = new MenuButton("VENUS");
            btnVenus.setOnMouseClicked(event -> {
                getChildren().add(menuVenus);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() - offset);
                                                                                                    //Transition pour accéder aux informations sur Venus.
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuVenus);
                tt1.setToX(menu1.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu1);
                });
            });

            MenuButton btnSaturne = new MenuButton("SATURNE");
            btnSaturne.setOnMouseClicked(event -> {
                getChildren().add(menuSaturne);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() - offset);
                                                                                                    //Transition pour accéder aux informations sur Saturne.
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuSaturne);
                tt1.setToX(menu1.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu1);
                });
            });

            MenuButton btnJupiter = new MenuButton("JUPITER");
            btnJupiter.setOnMouseClicked(event -> {
                getChildren().add(menuJupiter);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() - offset);
                                                                                                     //Transition pour accéder aux informations sur Jupiter.
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuJupiter);
                tt1.setToX(menu1.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu1);
                });
            });

            MenuButton btnMercure = new MenuButton("MERCURE");
            btnMercure.setOnMouseClicked(event -> {
                getChildren().add(menuMercure);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() - offset);
                                                                                                    //Transition pour accéder aux informations sur Mercure.
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuMercure);
                tt1.setToX(menu1.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu1);
                });
            });

            MenuButton btnNeptune = new MenuButton("NEPTUNE");
            btnNeptune.setOnMouseClicked(event -> {
                getChildren().add(menuNeptune);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() - offset);
                                                                                                    //Transition pour accéder aux informations sur Neptune.
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menuNeptune);
                tt1.setToX(menu1.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu1);
                });
            });

            // -------------------------------------------------- Menu Des infos des planètes  ---------------------------------------------------

            // ----------------------------------------- Info pour Mercure --------------------------------------------------
            MenuButton btnBack1 = new MenuButton("RETOUR");
            btnBack1.setOnMouseClicked(event -> {
                getChildren().add(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuMercure);
                tt.setToX(menuMercure.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menuMercure.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menuMercure);
                });
            });

            Label labeltitreMercure = new Label("Mercure");
            Label labelMercure = new Label("Mercure est une planète du Système solaire. Son éloignement au soleil est de 150 millions de km.\n" +
                    "Son rayon est de 2 439 km et sa masse est de 3.3011*10^24 kg.\n" +
                    "Mercure est une planète tellurique. Elle est près de trois fois plus petite et presque vingt fois moins massive que la Terre\n" +
                    "mais presque aussi dense.");
            labeltitreMercure.setTextFill(Color.web("#FFFFFF"));
            labelMercure.setTextFill(Color.web("#FFFFFF"));
            labeltitreMercure.setFont(Font.font(75));
            labelMercure.setFont(Font.font(25));    //Caractéristiques du texte.
            labelMercure.setTranslateX(-largeur / 3);
            labelMercure.setTranslateY(hauteur / 4);
            labeltitreMercure.setTranslateX(-largeur / 3);
            labeltitreMercure.setTranslateY(hauteur / 4);

            // ----------------------------------------- Info pour la Terre --------------------------------------------------
            MenuButton btnBack2 = new MenuButton("RETOUR");
            btnBack2.setOnMouseClicked(event -> {
                getChildren().add(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuTerre);
                tt.setToX(menuTerre.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menuTerre.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menuTerre);
                });
            });



            Label labeltitreTerre = new Label("La Terre");
            Label labelTerre = new Label("La Terre est une planète du Système solaire, elle s'est formée il y a 4.51 milliards d'années. \n" +
                    "Son rayon est d'environ 6 371 kilomètres et sa masse est estimée à 5.9722*20^24 kg. \n" +
                    "C'est une planète tellurique, c'est-à-dire une planète essentiellement rocheuse \n" +
                    "à noyau métallique, contrairement aux géantes gazeuses, telles que Jupiter, essentiellement constituées de gaz légers (hydrogène et hélium).\n ");
            labeltitreTerre.setTextFill(Color.web("#FFFFFF"));
            labelTerre.setTextFill(Color.web("#FFFFFF"));
            labeltitreTerre.setFont(Font.font(75));
            labelTerre.setFont(Font.font(25));      //Caractéristiques du texte.
            labelTerre.setTranslateX(-largeur/3);
            labelTerre.setTranslateY(hauteur/4);
            labeltitreTerre.setTranslateX(-largeur/3);
            labeltitreTerre.setTranslateY(hauteur/4);


            // ----------------------------------------- Info pour Mars --------------------------------------------------

            MenuButton btnBack3 = new MenuButton("RETOUR");
            btnBack3.setOnMouseClicked(event -> {
                getChildren().add(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuMars);
                tt.setToX(menuMars.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menuMars.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menuMars);
                });
            });

            Label labeltitreMars = new Label("Mars");
            Label labelMars = new Label("Mars est une planète du Système solaire. Son éloignement au Soleil est compris entre 206,6 à 249,2 millions de kilomètres,\n" +
                    "Son rayon est d'environ 3 396 km et sa masse est estimée à 6.4185*10^23 kg.\n" +
                    "C’est une planète tellurique, comme le sont Mercure, Vénus et la Terre, environ dix fois moins \n" +
                    "massive que la Terre mais dix fois plus massive que la Lune. Sa topographie présente des \n" +
                    "analogies aussi bien avec la Lune.");
            labeltitreMars.setTextFill(Color.web("#FFFFFF"));
            labelMars.setTextFill(Color.web("#FFFFFF"));
            labeltitreMars.setFont(Font.font(75));
            labelMars.setFont(Font.font(25));       //Caractéristiques du texte.
            labelMars.setTranslateX(-largeur/3);
            labelMars.setTranslateY(hauteur/4);
            labeltitreMars.setTranslateX(-largeur/3);
            labeltitreMars.setTranslateY(hauteur/4);

            // ----------------------------------------- Info pour Uranus --------------------------------------------------

            MenuButton btnBack4 = new MenuButton("RETOUR");
            btnBack4.setOnMouseClicked(event -> {
                getChildren().add(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuUranus);
                tt.setToX(menuUranus.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menuUranus.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menuUranus);
                });
            });

            Label labeltitreUranus = new Label("Uranus");
            Label labelUranus = new Label("Uranus est une planète du Système solaire. Son éloignement au soleil est de 3 milliards de km.\n" +
                    "Son rayon est de 25 559 km et sa masse de 8.6810*10^24 kg.\n" +
                    "Uranus est une planète géante, et plus précisément une planète géante de glaces. \n" +
                    "Uranus est de type Neptune froid.");
            labeltitreUranus.setTextFill(Color.web("#FFFFFF"));
            labelUranus.setTextFill(Color.web("#FFFFFF"));
            labeltitreUranus.setFont(Font.font(75));
            labelUranus.setFont(Font.font(25));         // Caracteristiques du texte
            labelUranus.setTranslateX(-largeur/3);
            labelUranus.setTranslateY(hauteur/4);
            labeltitreUranus.setTranslateX(-largeur/3);
            labeltitreUranus.setTranslateY(hauteur/4);

            // ----------------------------------------- Info pour Venus --------------------------------------------------

            MenuButton btnBack5 = new MenuButton("RETOUR");
            btnBack5.setOnMouseClicked(event -> {
                getChildren().add(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuVenus);
                tt.setToX(menuVenus.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menuVenus.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menuVenus);
                });
            });

            Label labeltitreVenus = new Label("Venus");
            Label labelVenus = new Label("Venus est une planète du Système solaire. Son éloignement au soleil est de 108,2 millions de km.\n" +
                    "Son rayon est de 6 051 km et sa masse de 4.8685*10^24 kg.\n" +
                    "Vénus est une planète tellurique. Elle est presque aussi grande que la Terre, son diamètre \n" +
                    "représente 95 % de celui de notre planète et a une masse équivalente aux quatre cinquièmes de \n" +
                    "celle de la Terre.");
            labeltitreVenus.setTextFill(Color.web("#FFFFFF"));
            labelVenus.setTextFill(Color.web("#FFFFFF"));
            labeltitreVenus.setFont(Font.font(75));
            labelVenus.setFont(Font.font(25));          // Caracteristiques du texte
            labelVenus.setTranslateX(-largeur/3);
            labelVenus.setTranslateY(hauteur/4);
            labeltitreVenus.setTranslateX(-largeur/3);
            labeltitreVenus.setTranslateY(hauteur/4);

            // ----------------------------------------- Info pour Saturne --------------------------------------------------

            MenuButton btnBack6 = new MenuButton("RETOUR");
            btnBack6.setOnMouseClicked(event -> {
                getChildren().add(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuSaturne);
                tt.setToX(menuSaturne.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menuSaturne.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menuSaturne);
                });
            });

            Label labeltitreSaturne = new Label("Saturne");
            Label labelSaturne = new Label("Saturne est une planète du Système solaire. Son éloignement au soleil est de 150 millions de km.\n" +
                    "Son rayon est de 60 268 km et sa masse est de 568.46*10^24 kg.\n" +
                    "Saturne est une planète géante, et plus précisément une géante gazeuse de type Jupiter froid. \n" +
                    "D'un diamètre d'environ neuf fois et demi celui de la Terre.\n");
            labeltitreSaturne.setTextFill(Color.web("#FFFFFF"));
            labelSaturne.setTextFill(Color.web("#FFFFFF"));
            labeltitreSaturne.setFont(Font.font(75));
            labelSaturne.setFont(Font.font(25));             // Caracteristiques du texte
            labelSaturne.setTranslateX(-largeur/3);
            labelSaturne.setTranslateY(hauteur/4);
            labeltitreSaturne.setTranslateX(-largeur/3);
            labeltitreSaturne.setTranslateY(hauteur/4);

            // ----------------------------------------- Info pour Jupiter --------------------------------------------------

            MenuButton btnBack7 = new MenuButton("RETOUR");
            btnBack7.setOnMouseClicked(event -> {
                getChildren().add(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuJupiter);
                tt.setToX(menuJupiter.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menuJupiter.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menuJupiter);
                });
            });

            Label labeltitreJupiter = new Label("Jupiter");
            Label labelJupiter = new Label("Jupiter est une planète du Système solaire. Son éloignement au Soleil est de 778 millions de km.\n" +
                    "Son rayon est de 69 911 km et sa masse est de 1.898*10^27 kg.\n" +
                    "Il s'agit d'une planète entièrement gazeuse dont le diamètre est de plus 12 fois celui \n" +
                    "de la terre et par conséquent son volume représenterait plus de 1000 fois le volume terrestre.\n");
            labeltitreJupiter.setTextFill(Color.web("#FFFFFF"));
            labelJupiter.setTextFill(Color.web("#FFFFFF"));
            labeltitreJupiter.setFont(Font.font(75));
            labelJupiter.setFont(Font.font(25));             // Caracteristiques du texte
            labelJupiter.setTranslateX(-largeur/3);
            labelJupiter.setTranslateY(hauteur/4);
            labeltitreJupiter.setTranslateX(-largeur/3);
            labeltitreJupiter.setTranslateY(hauteur/4);

            // ----------------------------------------- Info pour Neptune --------------------------------------------------

            MenuButton btnBack8 = new MenuButton("RETOUR");
            btnBack8.setOnMouseClicked(event -> {
                getChildren().add(menu1);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuNeptune);
                tt.setToX(menuNeptune.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menuNeptune.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menuNeptune);
                });
            });

            Label labeltitreNeptune= new Label("Neptune");
            Label labelNeptune = new Label("Neptune est une planète du Système solaire. Son éloignement au Soleil est de \n" +
                    "4,50 milliards de kilomètres. Son rayon est de 24 764 km et sa masse est estimée à 102.43*10^24kg,\n" +
                    "soit 17 fois la Terre. Neptune est une géante de glaces et plus précisément\n" +
                    "une planète de type Neptune froid.\n");
            labeltitreNeptune.setTextFill(Color.web("#FFFFFF"));
            labelNeptune.setTextFill(Color.web("#FFFFFF"));
            labeltitreNeptune.setFont(Font.font(75));
            labelNeptune.setFont(Font.font(25));             // Caracteristiques du texte
            labelNeptune.setTranslateX(-largeur/3);
            labelNeptune.setTranslateY(hauteur/4);
            labeltitreNeptune.setTranslateX(-largeur/3);
            labeltitreNeptune.setTranslateY(hauteur/4);


            // -------------------------------------------------- Ajouts ---------------------------------------------------

            menu0.getChildren().addAll(btnResume, btnchore, btnOptions, btnExit);
            menu1.getChildren().addAll(btnBack, btnMercure, btnTerre, btnMars, btnUranus, btnVenus, btnSaturne, btnJupiter, btnNeptune);
            menu2.getChildren().addAll(btnBack9, btnchore2, btnchore1);
            menuMercure.getChildren().addAll(btnBack1, labeltitreMercure, labelMercure);
            menuTerre.getChildren().addAll(btnBack2, labeltitreTerre, labelTerre);
            menuMars.getChildren().addAll(btnBack3, labeltitreMars, labelMars);
            menuUranus.getChildren().addAll(btnBack4, labeltitreUranus, labelUranus);
            menuVenus.getChildren().addAll(btnBack5, labeltitreVenus, labelVenus);
            menuSaturne.getChildren().addAll(btnBack6, labeltitreSaturne, labelSaturne);
            menuJupiter.getChildren().addAll(btnBack7, labeltitreJupiter, labelJupiter);
            menuNeptune.getChildren().addAll(btnBack8, labeltitreNeptune, labelNeptune);


            Rectangle bg = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize().getWidth(), Toolkit.getDefaultToolkit().getScreenSize().getHeight());
            bg.setFill(Color.GREY);
            bg.setOpacity(0.4);

            getChildren().addAll(bg, menu0);
        }
    }

    //Caracteristique pour la création de boutons identiques.
    private static class MenuButton extends StackPane {
        private Text text;

        public MenuButton(String name) {
            text = new Text(name);
            text.setFont(text.getFont().font(20));
            text.setFill(Color.WHITE);

            Rectangle bg = new Rectangle(500, 60);
            bg.setOpacity(0.6);
            bg.setFill(Color.BLACK);
            bg.setEffect(new GaussianBlur(3.5));

            setAlignment(Pos.CENTER_LEFT);
            setRotate(-0.5);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                bg.setTranslateX(10);
                text.setTranslateX(10); //Evenement lorsque la souris entre dans le bouton.
                bg.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });

            setOnMouseExited(event -> {
                bg.setTranslateX(0);
                text.setTranslateX(0); //Evenement lorsque l'utilisateur clique sur le bouton.
                bg.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
            });

            //Autre évènement lorsque l'on clique sur le bouton.
            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());

            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
