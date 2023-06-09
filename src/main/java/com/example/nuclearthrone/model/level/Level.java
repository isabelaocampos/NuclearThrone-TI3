package com.example.nuclearthrone.model.level;

import java.util.ArrayList;

import com.example.nuclearthrone.MainApplication;
//import com.example.nuclearthrone.model.entity.Avatar;
import com.example.nuclearthrone.model.entity.Entity;
//import com.example.nuclearthrone.model.entity.MovableEntity;
import com.example.nuclearthrone.model.entity.MovableEntity;
import com.example.nuclearthrone.model.entity.armery.Bullet;
//import com.example.nuclearthrone.model.entity.enviroment.Decoration;
import com.example.nuclearthrone.model.entity.enviroment.Background;
import com.example.nuclearthrone.model.entity.enviroment.Wall;
//import com.example.nuclearthrone.model.entity.item.Item;
//import com.example.nuclearthrone.model.entity.item.Slingshot;
//import com.example.nuclearthrone.model.entity.item.Staff;
//import com.example.nuclearthrone.model.entity.npc.Ball;
//import com.example.nuclearthrone.model.menus.Soundtrack;
//import com.example.nuclearthrone.model.util.Direction;

import com.example.nuclearthrone.model.entity.item.Item;
import com.example.nuclearthrone.model.entity.util.Direction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Level {

    public static final int MIN_ENEMIES = 1;
    public static final int MAX_ENEMIES = 6;
    public static final int MAX_ITEMS = 2;

    public ObservableList<Bullet> bullets = FXCollections.observableArrayList();
    public ObservableList<Wall> walls = FXCollections.observableArrayList();
    public ObservableList<Background> backgrounds = FXCollections.observableArrayList();
    public ObservableList<MovableEntity> entities = FXCollections.observableArrayList();
    public ObservableList<Item> items = FXCollections.observableArrayList();

    public Image background;

    private Level left;
    private Level right;
    private Level up;
    private Level down;

    private final int level;


    private Level(int level) {
        this.level = level;
    }




    private void start() {

    }

    private void destroy(){
    }


    private static ArrayList<Level> levels;
    private static int selected;

    private static void initializeLevels() {
        levels = new ArrayList<>();

        Level level1 = initLevel1();
        Level level2 = initLevel2();
        Level level3 = initLevel3();

        level1.right = level2;
        level2.left = level1;
        level2.right = level3;
        level3.left = level2;


        levels.add(level1);
        levels.add(level2);
        levels.add(level3);


        level1.start();
        level3.start();
        level2.start();


    }

    public static Level currentLevel() {
        if (levels == null)
            initializeLevels();
        return levels.get(selected);
    }

    public static boolean inGate(Entity entity) {
        Direction side = Entity.getSideOut(entity);
        switch (side) {
            case RIGHT -> {
                if (currentLevel().right != null) {
                    selected = levels.indexOf(currentLevel().right);
                    entity.setX(1);
                    return true;
                }
            }
            case LEFT -> {
                if (currentLevel().left != null) {
                    selected = levels.indexOf(currentLevel().left);
                    entity.setX(MainApplication.getWidth() - 11 - entity.getWidth());
                    return true;
                }
            }
            case UP -> {
                if (currentLevel().up != null) {
                    selected = levels.indexOf(currentLevel().up);
                    entity.setY(MainApplication.getHeight() - 30 - entity.getHeight());
                    return true;
                }
            }
            case DOWN -> {
                if (currentLevel().down != null) {
                    selected = levels.indexOf(currentLevel().down);
                    entity.setY(1);
                    return true;
                }
            }
            default -> {
            }
        }
        return false;
    }

    private static Level initLevel1() {
        Level level = new Level(0);

        String uri = "file:" + MainApplication.getFile("environment/decoration/mapa4.png").getPath();
        level.background = new Image(uri, MainApplication.getWidth(), MainApplication.getHeight(), false, false, false);

        // Top of the level
        for (int x = 0; x < MainApplication.getWidth(); x += 50) {
            if (x == 850) {
                level.walls.add(new Wall(x, 0, 10000, 0, "brick-above"));
            } else if (x == 300 || x == 800) {
                level.walls.add(new Wall(x, 0, 10000, 0, "brick-side-left"));
            } else if (x == 350 || x == 900) {
                level.walls.add(new Wall(x, 0, 10000, 0, "brick-side-right"));
            } else {
                level.walls.add(new Wall(x, 0, 10000, 0, "brick"));
            }
        }
        level.walls.add(new Wall(300, 50, 10000, 0, "brick-side-left"));
        level.walls.add(new Wall(300, 100, 10000, 0, "brick-side-left"));
        level.walls.add(new Wall(350, 50, 10000, 0, "brick-side-right"));
        level.walls.add(new Wall(350, 100, 10000, 0, "brick-side-right"));
        level.walls.add(new Wall(150, 150, 10000, 0, "brick-incorner-top-left"));
        level.walls.add(new Wall(150, 250, 10000, 0, "brick-end-left"));

        for (int x = 200; x < 500; x += 50) {
            if (x == 300) {
                level.walls.add(new Wall(x, 150, 10000, 0, "brick-corner-top-left"));
            } else if (x == 350) {
                level.walls.add(new Wall(x, 150, 10000, 0, "brick-corner-top-right"));
            } else {
                level.walls.add(new Wall(x, 150, 10000, 0, "brick-side-top"));
            }
            level.walls.add(new Wall(x, 200, 10000, 0, "brick-side-bottom"));
            level.walls.add(new Wall(x, 250, 10000, 0, "brick"));

        }

        level.walls.add(new Wall(500, 250, 10000, 0, "brick-end-right"));
        level.walls.add(new Wall(500, 150, 10000, 0, "brick-incorner-top-right"));
        level.walls.add(new Wall(150, 200, 10000, 0, "brick-incorner-bottom-left"));
        level.walls.add(new Wall(500, 200, 10000, 0, "brick-incorner-bottom-right"));

        // Right side bridge
        for (int x = 800; x < 950; x += 50) {
            for (int y = 50; y < 400; y += 50) {
                if (x == 800) {
                    level.walls.add(new Wall(x, y, 10000, 0, "brick-side-left"));
                } else if (x == 850) {
                    level.walls.add(new Wall(x, y, 10000, 0, "brick-above"));
                } else {
                    level.walls.add(new Wall(x, y, 10000, 0, "brick-side-right"));
                }
            }
        }

        level.walls.add(new Wall(550, 400, 10000, 0, "brick-incorner-top-left"));
        level.walls.add(new Wall(550, 450, 10000, 0, "brick-incorner-bottom-left"));
        level.walls.add(new Wall(550, 500, 10000, 0, "brick-end-left"));
        for (int x = 600; x < 950; x += 50) {
            if (x == 800) {
                level.walls.add(new Wall(x, 400, 10000, 0, "brick-corner-top-left"));
                level.walls.add(new Wall(x, 450, 10000, 0, "brick-side-bottom"));
            } else if (x == 850) {
                level.walls.add(new Wall(x, 400, 10000, 0, "brick-above"));
                level.walls.add(new Wall(x, 450, 10000, 0, "brick-side-bottom"));
            } else if (x == 900) {
                level.walls.add(new Wall(x, 400, 10000, 0, "brick-side-right"));
                level.walls.add(new Wall(x, 450, 10000, 0, "brick-incorner-bottom-right"));
            } else {
                level.walls.add(new Wall(x, 400, 10000, 0, "brick-side-top"));
                level.walls.add(new Wall(x, 450, 10000, 0, "brick-side-bottom"));
            }
            level.walls.add(new Wall(x, 500, 10000, 0, "brick"));

        }
        level.walls.add(new Wall(900, 500, 10000, 0, "brick-end-right"));

        // Little square at bottom
        level.walls.add(new Wall(150, MainApplication.getHeight() - 50, 10000, 0, "brick-side-left"));
        level.walls.add(new Wall(150, MainApplication.getHeight() - 100, 10000, 0, "brick-side-left"));
        level.walls.add(new Wall(150, MainApplication.getHeight() - 150, 10000, 0, "brick-incorner-top-left"));
        level.walls.add(new Wall(200, MainApplication.getHeight() - 150, 30, 0, "brick-side-top"));
        level.walls.add(new Wall(250, MainApplication.getHeight() - 150, 30, 0, "brick-side-top"));
        level.walls.add(new Wall(300, MainApplication.getHeight() - 150, 10000, 0, "brick-incorner-top-right"));
        level.walls.add(new Wall(300, MainApplication.getHeight() - 100, 10000, 0, "brick-side-right"));
        level.walls.add(new Wall(300, MainApplication.getHeight() - 50, 10000, 0, "brick-side-right"));


        return level;
    }

    private static Level initLevel2() {
        Level level = new Level(1);

        String uri = "file:" + MainApplication.getFile("environment/decoration/mapa2.png").getPath();
        level.background = new Image(uri, MainApplication.getWidth(), MainApplication.getHeight(), false, false, false);

        level.walls.add(new Wall(-45, 0, 10000, 1, "car2"));



        // Blue Obstacle
        for (int x = 250; x <= 450; x += 50) {
            for (int y = 125; y <= 275; y += 50) {
                level.walls.add(new Wall(x, y, 10000, 1, "tile062"));
            }
        }


        // Cows
        level.walls.add(new Wall(250, 550, 10000, 1, "car2"));
        level.walls.add(new Wall(350, 550, 10000, 1, "car2"));

        // Dog
        level.walls.add(new Wall(700, 20, 10000, 1, "car2"));

        // Cars
        level.walls.add(new Wall(1000, 200, 10000, 1, "car1"));
        level.walls.add(new Wall(800, 250, 10000, 1, "car2"));

        // Signals
        level.walls.add(new Wall(500, 500, 10000, 1, "car3"));
        level.walls.add(new Wall(800, 200, 10000, 1, "car1"));

        // Shape in the middle right
        for (int x = 825; x <= 1075; x += 50) {
            for (int y = 325; y <= 525; y += 50) {
                level.walls.add(new Wall(x, y, 10000, 1, "tile062"));
            }
        }
        for (int y = 375; y <= 475; y += 50) {
            level.walls.add(new Wall(825, y, 10000, 1, "tile062"));
            level.walls.add(new Wall(1075, y, 10000, 1, "tile062"));
        }
        for (int x = 825; x <= 1075; x += 50) {
            level.walls.add(new Wall(x, 525, 10000, 1, "tile062"));
        }
        for (int x = 875; x <= 1025; x += 50) {
            level.walls.add(new Wall(x, 525, 10000, 1, "tile062"));
        }

        level.walls.add(new Wall(1260, 0, 10000, 1, "tile062"));
        level.walls.add(new Wall(1260, 50, 10000, 1, "tile062"));

        return level;
    }


    private static Level initLevel3() {
        Level level = new Level(2);

        String uri = "file:" + MainApplication.getFile("environment/decoration/mapa2.png").getPath();
        level.background = new Image(uri, MainApplication.getWidth(), MainApplication.getHeight(), false, false, false);

        int columnWidth = 100;
        int columnSpacing = 150;

        level.walls.add(new Wall(-45, 570, 10000, 3, "car2"));
        level.walls.add(new Wall(-45, 620, 10000, 3, "car2"));

        // Left side walls (first column)
        for (int y = 0; y < MainApplication.getHeight(); y += 150) {
            level.walls.add(new Wall(MainApplication.getWidth() / 2 - 2 * columnSpacing - 2 * columnWidth + 50, y,33, 33, columnWidth, 3, "tile062"));
        }



        // Right side walls (first column)
        int rightColumnX = (int) (MainApplication.getWidth() - columnWidth - columnSpacing);
        for (int y = 0; y < MainApplication.getHeight(); y += 150) {
            level.walls.add(new Wall(rightColumnX, y,33, 33, columnWidth, 3, "tile062"));
        }


        return level;
    }


    public static Level getLevel(int level) {
        if (level >= levels.size())
            return null;
        return levels.get(level);
    }

    public static int getSelected() {
        return selected;
    }

    public static void resetLevels(){
        for(Level level :levels){
            level.destroy();
        }
        levels = null;
        selected = 0;
    }


}

