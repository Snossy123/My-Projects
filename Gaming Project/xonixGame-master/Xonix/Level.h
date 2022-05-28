#pragma once
#include <SFML\Graphics.hpp>
#include <string>
#include<SFML/Audio.hpp> 
#include "FilesHandler.h"


#define ScreenWidth 820
#define screenHeight 680
using namespace sf;
class Level
{
public : 
	struct enemy {
		//e=enemy
		// we put the function in the struct to make varibles of struct idientfied in the functions
		short expostion, eypostion, exvelocity, eyvelocity;
		enemy() {
			expostion = 400 - rand() % 50;
			eypostion = 400 - rand() % 50;

			exvelocity = 1 - rand() % 10;
			if (exvelocity == 0)
				exvelocity = 1 - rand() % 10;
			eyvelocity = 1 - rand() % 10;
			if (eyvelocity == 0)
				eyvelocity = 1 - rand() % 10;
		}
		 void motion(int grid[][62]) {
			expostion += exvelocity;
			if (grid[expostion / 10][eypostion / 10] == 1) {
				exvelocity = -exvelocity;
				expostion += exvelocity;
			}
			eypostion += eyvelocity;
			if (grid[expostion / 10][eypostion / 10] == 1) {
				eyvelocity = -eyvelocity;
				eypostion += eyvelocity;
			}

		}
	}enemies_struct[10];
	Level(short nEnemy);

	void load_images();
	void load_fonts();
	void load_sounds();
	void setTimer( int& second, int& minute, stringstream& time_string);
	void setLives(short &hearts);
	void setBoundaries();
	int	 getLives();
	void setTimeText();
	void setScoreText(Font& Arial_font);
	void setHeartText(Font& Arial_font, Text& heartText);
	void setPercentText(Font& Arial_font, Text& PercentText);
	void updatePercenetText();
	void updateHeartText();
	void setPercent();
	void setDifficulty();
	void moveEnemy(short nEnemy);
	void rules_of_draw(int ex,int ey);
	void setBrush(int& xpos, int& ypos);
	void setEnemiesShapes(short& nEnemy, RectangleShape enemies_shapes[], short scale);
	void movePlayer(int& xpos, int& ypos, char& dir);
	void CalculateScore();
	void scorecalc(int& score, int time);
	void drawArea(Sprite& Sgrid, RenderWindow& window, Texture& image, Texture& image2, int grid[][62]);
	void setPlayerShape();
	//void setEnemiesShapes(short& nEnemy, RectangleShape enemies_shapes[], short scale);
	void IntialiseLevel(string levelsFile, string levelName);
	void checkCollision(short nEnemy, RectangleShape enemies_shapes[], int& xpos, int& ypos, bool& play);
	int* getGrid();

	int  grid[82][62] = {0};

	int checkBoundaries()
	{
		int percent = 0;
		for (int i = 0; i < 82; i++)
		{
			for (int j = 0; j < 62; j++)
			{
				if (i == 0 || i == 81 || j == 0 || j == 61 || i == 1 || i == 80 || j == 1 || j == 60)
				{
					grid[i][j] = 1;
				}
				else if (grid[i][j] == 1)
				{
					percent++;

				}



			}
		}
		return percent;
	}

	void checkCompletion(int& percent, short& oldpercent)
	{
		oldpercent = percent;
		percent = checkBoundaries() / (44);
		if (oldpercent != percent)
		{
			oldpercent = percent;
			completion.play();
			score += 10;
		}
	}
private:
	char search = 'u';

	Texture image1, image2, image3,Enemy;
	Sprite sprite3,Sgrid;
	short heart = 3, old_percent;
	int xpos = 0, ypos = 0, seconds=0,minutes=0,score,highscore,bonus,percent;
	bool play = true,defaultmode=false,replay=false;
	char dir = '5';
	Clock clock;
	
	Event event;
	Music music;
	SoundBuffer collisionBuf, losingBuf, completionBuf, windowPressBuf;
	Sound completion, losing, windowPressed, collisionSound;

	Font arial, number_font,menu;
	FilesHandler textFile;
	RectangleShape player, enemies_shapes[10],bound;

	
	
	
	Text time_text, PercentText, heartText,labelscore;
	stringstream scoreString, time_string, areaString, heartString, nameString;
	
		
	
};

