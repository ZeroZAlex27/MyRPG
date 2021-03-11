package utils;

import com.badlogic.gdx.physics.box2d.*;
import com.zeroz.games.Main;
import screens.BattleScreen;
import screens.mapScreens.Map1Screen;
import screens.mapScreens.Map2Screen;
import sprites.Enemy;
import sprites.Player;

public class WorldContactListener implements ContactListener {

    Main mainGame;

    public WorldContactListener(Main mainGame) {
        this.mainGame = mainGame;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture objectA = contact.getFixtureA();
        Fixture objectB = contact.getFixtureB();

        if(objectA.getUserData() instanceof Player && objectB.getUserData() instanceof Enemy) {
            mainGame.setScreen(new BattleScreen(mainGame));
        }
        if(objectA.getUserData() instanceof Player && objectB.getUserData().equals("nextLevelLayer")) {
            if(mainGame.getScreen() instanceof Map1Screen) {
                mainGame.setScreen(new Map2Screen(mainGame));
            }
            else if (mainGame.getScreen() instanceof  Map2Screen) {
                mainGame.setScreen(new Map1Screen(mainGame));
            }

        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
