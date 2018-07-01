package game;

import gameoflife.GameOfLifeBoard;
import java.util.Random;

/**
 *
 * @author j3kaiii
 */
public class PersonalBoard extends GameOfLifeBoard{
    

    //создаем поле с заданными размерами
    public PersonalBoard(int width, int height) {
        super(width, height);
    }

    @Override
    //случайное распределение живых и мертвых клеток
    public void initiateRandomCells(double d) {
        
        for (int i = 0; i < super.getWidth(); i++) {
            for (int j = 0; j < super.getHeight(); j++) {
                double random = new Random().nextDouble();
                if (random <= d) {
                    super.getBoard()[i][j] = true;
                } else {
                    super.getBoard()[i][j] = false;
                }
            }
        }
    }

    @Override
    //проверяем клетку на наличие жизни
    public boolean isAlive(int i, int i1) {
        if (i < super.getWidth() && 
            i >= 0 &&
            i1 < super.getHeight() &&
            i1 >= 0) {
            //если живая - возвращяем true
            return super.getBoard()[i][i1];
        }
        return false;
    }

    @Override
    //делаем клетку живой
    public void turnToLiving(int i, int i1) {
        if (i < super.getWidth() && 
            i >= 0 &&
            i1 < super.getHeight() &&
            i1 >= 0)
        super.getBoard()[i][i1] = true;
    }

    @Override
    //делаем клетку мертвой
    public void turnToDead(int i, int i1) {
        if (i < super.getWidth() && 
            i >= 0 &&
            i1 < super.getHeight() &&
            i1 >= 0)
        super.getBoard()[i][i1] = false;
    }

    @Override
    //находим колличество живых соседей
    public int getNumberOfLivingNeighbours(int i, int i1) {
        int result = 0; // изначальный результат
        int startx = i-1, starty = i1-1, endx = i+1, endy = i1+1; // если клетка не прикасается к краям поля
        //если клетка в левом ряду
        if (i == 0) {
            startx = i;
            endx = i+1;
        }
        //если клетка в правом ряду
        if (i == super.getWidth() - 1) {
            startx = i-1;
            endx = i;
        }
        //если клетка в верхней строке
        if (i1 == 0) {
            starty = i1;
            endy = i1+1;
        }
        //если клетка в нижней строке
        if (i1 == super.getHeight() - 1) {
            starty = i1-1;
            endy = i1;
        }
        
        for (int x = startx; x <=endx; x++) {
            for (int y = starty; y <= endy; y++) {
                if (x == i && y == i1) {
                    continue;
                }
                if (super.getBoard()[x][y] == true) {
                    result++;
                }
            }
        }
        
        return result;
    }

    @Override
    public void manageCell(int i, int i1, int i2) {
        /*
        по правилам клетка живая, если есть 2 или 3 живых соседа
        если клетка мертвая и есть 3 соседа - клетка оживает
        если клетка живая и соседей до 2 или больше 3 - клетка умирает
        */
        if (i2 < 2 || i2 > 3) turnToDead(i, i1);
        else if (super.getBoard()[i][i1] && i2 > 1 && i2 < 4) super.getBoard()[i][i1] = true;
        else if (super.getBoard()[i][i1] == false && i2 == 3) super.getBoard()[i][i1] = true;
    }

}
