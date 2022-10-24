import random, time, pygame, sys
from pygame.locals import *

FPS = 25
WINDOWWIDTH = 1000
WINDOWHEIGHT = 550
BOXSIZE = 20
BOARDWIDTH = 10
BOARDHEIGHT = 20
BLANK = '.'

MOVESIDEWAYSFREQ = 0.08
MOVEDOWNFREQ = 0.1

XMARGIN = int((WINDOWWIDTH - BOARDWIDTH * BOXSIZE)/1.35)
TOPMARGIN = WINDOWHEIGHT - (BOARDHEIGHT * BOXSIZE) - 5
TOPMARGIN2 = 425 - (BOARDHEIGHT * BOXSIZE) - 5
XMARGIN2 = int((WINDOWWIDTH - BOARDWIDTH * BOXSIZE)/4)
XMARGIN3 = int(10)
XMARGIN4 = int(WINDOWWIDTH-135)
#               R    G    B
WHITE       = (255, 255, 255)
GRAY        = (185, 185, 185)
BLACK       = (  0,   0,   0)
RED         = (155,   0,   0)
LIGHTRED    = (175,  20,  20)
GREEN       = (  0, 155,   0)
LIGHTGREEN  = ( 20, 175,  20)
BLUE        = (  1, 36, 207 )
LIGHTBLUE   = ( 16,  51, 207)
YELLOW      = (193, 196,   0)
LIGHTYELLOW = (213, 216,  20)
PINK        = ( 222, 0, 209 )
LIGHTPINK   = (242, 20, 229)

LINECOLOR= (100, 96, 99)
BORDERCOLOR = GRAY
BGCOLOR = BLACK
TEXTCOLOR = WHITE
TEXTSHADOWCOLOR = GRAY
COLORS      = (     BLUE,      GREEN,      RED,      YELLOW,PINK     ,GRAY)
LIGHTCOLORS = (LIGHTBLUE, LIGHTGREEN, LIGHTRED, LIGHTYELLOW,LIGHTPINK,GRAY)
assert len(COLORS) == len(LIGHTCOLORS) # Chaque couleur est associée a une couleur claire

TEMPLATEWIDTH = 5
TEMPLATEHEIGHT = 5

S_SHAPE_TEMPLATE = [['.....',
                     '.....',
                     '..OO.',
                     '.OO..',
                     '.....'],
                    ['.....',
                     '..O..',
                     '..OO.',
                     '...O.',
                     '.....']]

Z_SHAPE_TEMPLATE = [['.....',
                     '.....',
                     '.OO..',
                     '..OO.',
                     '.....'],
                    ['.....',
                     '..O..',
                     '.OO..',
                     '.O...',
                     '.....']]

I_SHAPE_TEMPLATE = [['..O..',
                     '..O..',
                     '..O..',
                     '..O..',
                     '.....'],
                    ['.....',
                     '.....',
                     'OOOO.',
                     '.....',
                     '.....']]

O_SHAPE_TEMPLATE = [['.....',
                     '.....',
                     '.OO..',
                     '.OO..',
                     '.....']]

J_SHAPE_TEMPLATE = [['.....',
                     '.O...',
                     '.OOO.',
                     '.....',
                     '.....'],
                    ['.....',
                     '..OO.',
                     '..O..',
                     '..O..',
                     '.....'],
                    ['.....',
                     '.....',
                     '.OOO.',
                     '...O.',
                     '.....'],
                    ['.....',
                     '..O..',
                     '..O..',
                     '.OO..',
                     '.....']]

L_SHAPE_TEMPLATE = [['.....',
                     '...O.',
                     '.OOO.',
                     '.....',
                     '.....'],
                    ['.....',
                     '..O..',
                     '..O..',
                     '..OO.',
                     '.....'],
                    ['.....',
                     '.....',
                     '.OOO.',
                     '.O...',
                     '.....'],
                    ['.....',
                     '.OO..',
                     '..O..',
                     '..O..',
                     '.....']]

T_SHAPE_TEMPLATE = [['.....',
                     '..O..',
                     '.OOO.',
                     '.....',
                     '.....'],
                    ['.....',
                     '..O..',
                     '..OO.',
                     '..O..',
                     '.....'],
                    ['.....',
                     '.....',
                     '.OOO.',
                     '..O..',
                     '.....'],
                    ['.....',
                     '..O..',
                     '.OO..',
                     '..O..',
                     '.....']]

ESCALIER_SHAPE_TEMPLATE=[['.O...',
                          '.OO..',
                          '.OOO.',
                          '.....',
                          '.....'],
                         ['.000.',
                          '.OO..',
                          '.O...',
                          '.....',
                          '.....'],
                         ['.000.',
                          '..00.',
                          '...0.',
                          '.....',
                          '.....'],
                         ['...0.',
                          '..00.',
                          '.000.',
                          '.....',
                          '.....']]
                 
COIN_SHAPE_TEMPLATE=[['.OOO.',
                      '.O...',
                      '.O...',
                      '.....',
                      '.....'],
                     ['.000.',
                      '...0.',
                      '...0.',
                      '.....',
                      '.....'],
                     ['...0.',
                      '...0.',
                      '.000.',
                      '.....',
                      '.....'],
                     ['.0...',
                      '.0...',
                      '.000.',
                      '.....',
                      '.....']]

G_GAMEOVER=[["000..",
             "0....",
             "0..0.",
             "0000.",
             ".....",]]

A_GAMEOVER=[["0000.",
             "0..0.",
             "0000.",
             "0..0.",
             ".....",]]

M_GAMEOVER=[["0000.",
             "0..0.",
             "0..0.",
             "0..0.",
             ".....",]]

E_GAMEOVER=[["0000.",
             "0....",
             "00...",
             "0....",
             "0000.",]]

O_GAMEOVER=[["..00.",
             ".0..0",
             ".0..0",
             "..00.",
             ".....",]]

V_GAMEOVER=[[".....",
             ".0..0",
             ".0..0",
             "..00.",
             ".....",]]

R_GAMEOVER=[[".0000",
             ".0..0",
             ".0000",
             ".0.0.",
             ".0..0",]]


PIECES = {'S': S_SHAPE_TEMPLATE,
          'Z': Z_SHAPE_TEMPLATE,
          'J': J_SHAPE_TEMPLATE,
          'L': L_SHAPE_TEMPLATE,
          'I': I_SHAPE_TEMPLATE,
          'O': O_SHAPE_TEMPLATE,
          'T': T_SHAPE_TEMPLATE,

          'ESCALIER':ESCALIER_SHAPE_TEMPLATE,
          'COIN'  :COIN_SHAPE_TEMPLATE,

          "G_GAMEOVER": G_GAMEOVER,
          "A_GAMEOVER": A_GAMEOVER,
          "M_GAMEOVER": M_GAMEOVER,
          "E_GAMEOVER": E_GAMEOVER,
          "O_GAMEOVER": O_GAMEOVER,
          "V_GAMEOVER": V_GAMEOVER,
          "R_GAMEOVER": R_GAMEOVER
          }

FORMES_NORMALES=['S','Z','J','L','I','O','T']
FORMES_MALUS=['ESCALIER','COIN']
LEVELS=[]
scoreLevel=0
for i in range(30):
    scoreLevel+=500*i
    LEVELS.append(scoreLevel)

def main():
    global FPSCLOCK, DISPLAYSURF, BASICFONT, BIGFONT,malusOn,pieceFantomeOn
    pygame.init()
    FPSCLOCK = pygame.time.Clock()
    DISPLAYSURF = pygame.display.set_mode((WINDOWWIDTH, WINDOWHEIGHT))
    BASICFONT = pygame.font.Font('freesansbold.ttf', 18)
    BIGFONT = pygame.font.Font('freesansbold.ttf', 70)
    pygame.display.set_caption('Tetris 1v1')

    accueil()
    malusOn=chooseMode("malus")
    pieceFantomeOn=chooseMode("fantome")
    while True: # Boucle du jeu
        runGame()
        

def runGame():
    # Variables pour le lancement de la partie
    board = getBlankBoard()
    lastMoveDownTime = time.time()
    lastMoveSidewaysTime = time.time()
    lastFallTime = time.time()
    movingDown = False 
    movingLeft = False
    movingRight = False
    score = 0
    totalLigne=0
    level=1
    level2=1
    level, fallFreq = calculateLevelAndFallFreq(score,level)
    board2 = getBlankBoard()
    lastMoveDownTime2 = time.time()
    lastMoveSidewaysTime2 = time.time()
    lastFallTime2 = time.time()
    movingDown2 = False
    movingLeft2 = False
    movingRight2 = False
    score2 = 0
    totalLigne2=0
    level2, fallFreq2 = calculateLevelAndFallFreq(score2,level2)
    IS_LOST=False
    IS_LOST2=False
    malusJoueur1=0
    malusJoueur2=0
    fallingPiece = getNewPiece()
    nextPiece = getNewPiece()
    
    
    fallingPiece2 = getNewPiece()
    nextPiece2 = getNewPiece()


    while True: 
        if IS_LOST and IS_LOST2:
            endGame(score,score2)
            return # met fin à la partie
        
        checkForQuit()
        for event in pygame.event.get(): 
            if event.type == KEYUP:
                if (event.key == K_p):
                   # Mettre le jeu en pause
                    DISPLAYSURF.fill(BGCOLOR)
                    showTextScreen('Paused') # Affichage de la pause
                    lastFallTime = time.time()
                    lastMoveDownTime = time.time()
                    lastMoveSidewaysTime = time.time()
                    lastFallTime2 = time.time()
                    lastMoveDownTime2 = time.time()
                    lastMoveSidewaysTime2 = time.time()
                #JOUEUR 1
                if (event.key == K_LEFT):
                    movingLeft = False
                elif (event.key == K_RIGHT):
                    movingRight = False
                elif (event.key == K_DOWN):
                    movingDown = False
                #JOUEUR 2
                if (event.key == K_q):
                    movingLeft2 = False
                elif (event.key == K_d):
                    movingRight2 = False
                elif (event.key == K_s):
                    movingDown2 = False

            elif event.type == KEYDOWN:
                #JOUEUR 1

                # Vérifie si le mouvement effectué par le joueur est possible ou non, ici pour bouger la pièce vers la gauche
                if (event.key == K_LEFT) and isValidPosition(board, fallingPiece, adjX=-1):
                    fallingPiece['x'] -= 1
                    movingLeft = True # Active l'évênement correspondant
                    movingRight = False # Désactive les autres évênements
                    lastMoveSidewaysTime = time.time()

                # Vérifie si le mouvement effectué par le joueur est possible ou non, ici pour bouger la pièce vers la droite
                elif (event.key == K_RIGHT ) and isValidPosition(board, fallingPiece, adjX=1):
                    fallingPiece['x'] += 1
                    movingRight = True # Active l'évênement correspondant
                    movingLeft = False # Désactive les autres évênements
                    lastMoveSidewaysTime = time.time()

                # Vérifie si le mouvement effectué par le joueur est possible ou non, ici pour effectuer une rotation de la pièce
                elif (event.key == K_UP):
                    if not(malusOn and malusJoueur1==1): # Vérifie si le joueur n'est pas affecté par un malus
                        fallingPiece['rotation'] = (fallingPiece['rotation'] + 1) % len(PIECES[fallingPiece['shape']])
                        if not isValidPosition(board, fallingPiece):
                            fallingPiece['rotation'] = (fallingPiece['rotation'] - 1) % len(PIECES[fallingPiece['shape']])

                # Vérifie si le mouvement effectué par le joueur est possible ou non, ici pour faire chuter la pièce plus rapidement
                elif (event.key == K_DOWN):
                    movingDown = True # Active l'évênement correspondant
                    if isValidPosition(board, fallingPiece, adjY=1):
                        fallingPiece['y'] += 1
                    lastMoveDownTime = time.time()
                    
                elif event.key == K_KP0:
                    movingDown = False  # Désactive les autres évênements
                    movingLeft = False 
                    movingRight = False 
                    for i in range(1, BOARDHEIGHT):
                        if not isValidPosition(board, fallingPiece, adjY=i):
                            break
                    fallingPiece['y'] += i - 1
                    lastFallTime=fallFreq


                        

               #JOUEUR 2

                 # Vérifie si le mouvement effectué par le joueur est possible ou non, ici pour bouger la pièce vers la gauche
                if (event.key == K_q) and isValidPosition(board2, fallingPiece2, adjX=-1):
                    fallingPiece2['x'] -= 1
                    movingLeft2 = True # Active l'évênement correspondant
                    movingRight2 = False # Désactive les autres évênements
                    lastMoveSidewaysTime2 = time.time()

                # Vérifie si le mouvement effectué par le joueur est possible ou non, ici pour bouger la pièce vers la droite
                elif (event.key == K_d) and isValidPosition(board2, fallingPiece2, adjX=1):
                    fallingPiece2['x'] += 1
                    movingRight2 = True # Active l'évênement correspondant
                    movingLeft2 = False # Désactive les autres évênements
                    lastMoveSidewaysTime2 = time.time()

                # Vérifie si le mouvement effectué par le joueur est possible ou non, ici pour effectuer une rotation de la pièce
                elif (event.key == K_z):
                    if not(malusOn and malusJoueur2==1): # Vérifie si le joueur n'est pas affecté par un malus
                        fallingPiece2['rotation'] = (fallingPiece2['rotation'] + 1) % len(PIECES[fallingPiece2['shape']])
                        if not isValidPosition(board2, fallingPiece2):
                            fallingPiece2['rotation'] = (fallingPiece2['rotation'] - 1) % len(PIECES[fallingPiece2['shape']])

                # Vérifie si le mouvement effectué par le joueur est possible ou non, ici pour faire chuter la pièce plus rapidement
                elif (event.key == K_s):
                    movingDown2 = True # Active l'évênement correspondant
                    if isValidPosition(board2, fallingPiece2, adjY=1):
                        fallingPiece2['y'] += 1
                    lastMoveDownTime2 = time.time()
                    
                elif event.key == K_SPACE:
                    movingDown2 = False # Désactive les autres évênements
                    movingLeft2 = False
                    movingRight2 = False
                    for i in range(1, BOARDHEIGHT):
                        if not isValidPosition(board2, fallingPiece2, adjY=i):
                            break
                    fallingPiece2['y'] += i - 1
                    lastFallTime2=fallFreq2

        # Contrôle des mouvements de la pièce par le joueur
        #JOUEUR1
        if (movingLeft or movingRight) and time.time() - lastMoveSidewaysTime > MOVESIDEWAYSFREQ:
            if movingLeft and isValidPosition(board, fallingPiece, adjX=-1):
                fallingPiece['x'] -= 1
            elif movingRight and isValidPosition(board, fallingPiece, adjX=1):
                fallingPiece['x'] += 1
            lastMoveSidewaysTime = time.time()

        if movingDown and time.time() - lastMoveDownTime > MOVEDOWNFREQ and isValidPosition(board, fallingPiece, adjY=1):
            fallingPiece['y'] += 1
            lastMoveDownTime = time.time()
       #JOUEUR2
        if (movingLeft2 or movingRight2) and time.time() - lastMoveSidewaysTime2 > MOVESIDEWAYSFREQ:
            if movingLeft2 and isValidPosition(board2, fallingPiece2, adjX=-1):
                fallingPiece2['x'] -= 1
            elif movingRight2 and isValidPosition(board2, fallingPiece2, adjX=1):
                fallingPiece2['x'] += 1
            lastMoveSidewaysTime2 = time.time()

        if movingDown2 and time.time() - lastMoveDownTime2 > MOVEDOWNFREQ and isValidPosition(board2, fallingPiece2, adjY=1):
            fallingPiece2['y'] += 1
            lastMoveDownTime2 = time.time()  # La pièce chute si c'est possible

       
        #JOUEUR1
        if not IS_LOST:
            if time.time() - lastFallTime > fallFreq:
                  # Verifie si la pièce est tout en bas
                if not isValidPosition(board, fallingPiece, adjY=1):
                    # La pièce a attéri, on l'ajoute au board
                    addToBoard(board, fallingPiece)
                    ligneCompleted=removeCompleteLines(board)
                    totalLigne+=ligneCompleted
                    # Calcul du score à chaque ligne complétée
                    if ligneCompleted==1:
                        score+=50*level
                    elif ligneCompleted==2:
                        score+=150*level
                    elif ligneCompleted==3:
                        score+=350*level  
                    elif ligneCompleted>3:
                        score+=1000*level
                    # Calcul du score en fonction du level
                    score+=10*level
                    level, fallFreq = calculateLevelAndFallFreq(score,level)
                    
                    fallingPiece = nextPiece#la pièce est tombée donc on prend la prochaine pièce
                    nextPiece = getNewPiece()
                    lastFallTime = time.time()
                    if not isValidPosition(board, fallingPiece): # Si on ne peut pas placer la prochaine pièce , le jeu est perdu
                        IS_LOST=True
                        fallingPiece['y']=-10000
                        board=GameOver(board)
                        
                    malusJoueur1=0 #reset du malus une fois que le piece est tombée

                     # Conditions pour que les différents malus s'activent 
                    if malusOn:
                        if ligneCompleted==2: # active un malus qui accélère la chute de la pièce en train de chuter
                            fallFreq2=fallFreq2/4
                            fallingPiece2["color"]=4
                        if ligneCompleted==3:
                            if fallingPiece2["shape"]!="O":
                                fallingPiece2["color"]=5
                                malusJoueur2=1 #active un malus empêchant de tourner la piece
                            else:
                                fallFreq2=fallFreq2/5
                                fallingPiece2["color"]=4
                        elif ligneCompleted==4: # active un malus créant une pièce différente
                            nextPiece2=getNewPiece(is_normal=False)
                else:
                     # Tant que la pièce n'a pas touché le sol, elle continue de chuter
                    fallingPiece['y'] += 1 # Vitesse de la chute
                    lastFallTime = time.time()
        
       #JOUEUR2
        if not IS_LOST2:
            if time.time() - lastFallTime2 > fallFreq2:
                
                if not isValidPosition(board2, fallingPiece2, adjY=1):
                    # Verifie si la pièce est tout en bas
                    addToBoard(board2, fallingPiece2)
                    ligneCompleted2=removeCompleteLines(board2)
                    totalLigne2+=ligneCompleted2
                    # Calcul du score à chaque ligne complétée
                    if ligneCompleted2==1:
                        score2 +=50*level2
                    elif ligneCompleted2==2:
                        score2 +=150*level2
                    elif ligneCompleted2==3:
                        score2 +=350*level2
                    elif ligneCompleted2>3:
                        score2 +=1000*level2

                    # Calcul du score en fonction du level
                    score2 +=10*level2
                    level2, fallFreq2 = calculateLevelAndFallFreq(score2,level2)
                    fallingPiece2 = nextPiece2 #la pièce est tombée donc on prend la prochaine pièce
                    nextPiece2 = getNewPiece()
                    lastFallTime2 = time.time()
                    if not isValidPosition(board2, fallingPiece2): # Si on ne peut pas placer la prochaine pièce , le jeu est perdu
                        IS_LOST2=True
                        fallingPiece2['y']=-10000
                        board2=GameOver(board)
                        
                    malusJoueur2=0 #reset du malus une fois que le piece est tombée

                    # Conditions pour que les différents malus s'activent 
                    if malusOn:
                        if ligneCompleted2==2: # active un malus qui accélère la chute de la pièce en train de chuter
                            fallFreq=fallFreq/4
                            fallingPiece["color"]=4
                        if ligneCompleted2==3:
                            if fallingPiece["shape"]!="O":
                                fallingPiece["color"]=len(COLORS)-1
                                malusJoueur1=1 #active un malus empêchant de tourner la piece
                            else:
                                fallFreq=fallFreq/5
                                fallingPiece["color"]=4
                        elif ligneCompleted2==4: # active un malus créant une pièce différente
                            nextPiece=getNewPiece(is_normal=False) 
                    
                else:
                     # Tant que la pièce n'a pas touché le sol, elle continue de chuter
                    fallingPiece2['y'] += 1 # Vitesse de la chute
                    lastFallTime2 = time.time()

       # Affichage de tout ce qui est necessaire à la compréhension de la partie
     # Score, niveau, pièce à venir
        
        DISPLAYSURF.fill(BGCOLOR)
        drawBoard(board,board2)
        drawStatus(score, level,score2,level2,totalLigne,totalLigne2)
        drawNextPiece(nextPiece,True)
        drawNextPiece(nextPiece2,False)
        if (fallingPiece != None and not IS_LOST):
            if pieceFantomeOn:
                fantomPiece=getFantomPiece(fallingPiece,board)
                drawPiece(fantomPiece,True)
            drawPiece(fallingPiece,True)
        if (fallingPiece2!=None and not IS_LOST2):
            if pieceFantomeOn:
                fantomPiece=getFantomPiece(fallingPiece2,board2)
                drawPiece(fantomPiece,False)
            drawPiece(fallingPiece2,False)
        
        pygame.display.update()
        FPSCLOCK.tick(FPS)


def GameOver(board):
    board=getBlankBoard()
    # Pièces permettant d'afficher le "game over" sur l'écran du joueur qui vient de perdre
    G = {'shape': "G_GAMEOVER",
                    'rotation': 0,
                    'x': 0,
                    'y': 0, 
                    'color': 2}
    A = {'shape': "A_GAMEOVER",
                    'rotation': 0,
                    'x': 0,
                    'y': 5, 
                    'color': 2}
    M = {'shape': "M_GAMEOVER",
                    'rotation': 0,
                    'x': 0,
                    'y': 10, 
                    'color': 2}
    E1 = {'shape': "E_GAMEOVER",
                    'rotation': 0,
                    'x': 0,
                    'y': 15, 
                    'color': 2}
    O = {'shape': "O_GAMEOVER",
                    'rotation': 0,
                    'x': 5,
                    'y': 0, 
                    'color': 2}
    V = {'shape': "V_GAMEOVER",
                    'rotation': 0,
                    'x': 5,
                    'y': 4, 
                    'color': 2}
    E2 = {'shape': "E_GAMEOVER",
                    'rotation': 0,
                    'x': 6,
                    'y': 9, 
                    'color': 2}
    R = {'shape': "R_GAMEOVER",
                    'rotation': 0,
                    'x': 5,
                    'y': 15, 
                    'color': 2}
    Liste=[G,A,M,E1,O,V,E2,R]
    for i in Liste:
        addToBoard(board,i)
    return(board)
    
def chooseMode(mode):
    # Commandes permettant de choisir si l'on veut jouer avec les malus ou non
    if mode=="malus":
        DISPLAYSURF.blit(pygame.image.load("image/malus.jpg"),(0,0))
    elif mode=="fantome":
        DISPLAYSURF.blit(pygame.image.load("image/fantome1v1.jpg"),(0,0))
    unready=True
    while unready:
        pygame.display.update()
        a=checkForKeyPress()
        if a == K_F1:
            pygame.display.update()
            FPSCLOCK.tick()
            unready=False
            return(True)
        elif a == K_F2:
            pygame.display.update()
            FPSCLOCK.tick()
            unready=False
            return(False)
            

def accueil():
    # Affichage des différentes commandes pour jouer
    DISPLAYSURF.blit(pygame.image.load("image/commandes 2.jpg"),(0,0))
    
    while checkForKeyPress() == None:
        pygame.display.update()
        FPSCLOCK.tick()

def makeTextObjs(text, font, color):
    surf = font.render(text, True, color)
    return surf, surf.get_rect()


def terminate():
    pygame.quit()
    sys.exit()



def checkForKeyPress():
    for event in pygame.event.get():
        if event.type == QUIT:       # Fin de la partie si l'on décide de fermer la fenêtre
            terminate()
        elif event.type == KEYDOWN:
            if event.key == K_ESCAPE:   # Touche de secours pour quitter 
                terminate()
            else:
                return event.key   # Touche actionnée donc l'évênement qui en découle est retourné
    # Pas d'action donc il ne se passe rien  
    return None

def endGame(score,score2):
    if score>score2:
        maxScoreSurf, maxScoreRect = makeTextObjs('Le joueur 2 a gagné ! ', BIGFONT, TEXTCOLOR)

    elif score2>score:
        maxScoreSurf, maxScoreRect = makeTextObjs('Le joueur 1 a gagné ! ', BIGFONT, TEXTCOLOR)

    else:
        maxScoreSurf, maxScoreRect = makeTextObjs('Egalité ! ', BIGFONT, TEXTCOLOR)

    maxScoreRect.center=(int(WINDOWWIDTH / 2), int(WINDOWHEIGHT / 2)+20)
    DISPLAYSURF.blit(maxScoreSurf, maxScoreRect)

    while checkForKeyPress() == None:
        pygame.display.update()
        FPSCLOCK.tick()
  # appuyer sur une touche relance une nouvelle partie

def showTextScreen(text):
    # Police du texte
    # Texte affiché au centre de l'accueil tant qu'aucune touche n'a été actionnée
    # Ombre du texte
    titleSurf, titleRect = makeTextObjs(text, BIGFONT, TEXTSHADOWCOLOR)
    titleRect.center = (int(WINDOWWIDTH / 2), int(WINDOWHEIGHT / 2))
    DISPLAYSURF.blit(titleSurf, titleRect)

    # Affichage du texte
    titleSurf, titleRect = makeTextObjs(text, BIGFONT, TEXTCOLOR)
    titleRect.center = (int(WINDOWWIDTH / 2) - 3, int(WINDOWHEIGHT / 2) - 3)
    DISPLAYSURF.blit(titleSurf, titleRect)

   # Affichage de l'eventuel (" Press key to ...")
    pressKeySurf, pressKeyRect = makeTextObjs(' ', BASICFONT, TEXTCOLOR)
    pressKeyRect.center = (int(WINDOWWIDTH / 2), int(WINDOWHEIGHT / 2) + 100)
    DISPLAYSURF.blit(pressKeySurf, pressKeyRect)

    while checkForKeyPress() == None:
        pygame.display.update()
        FPSCLOCK.tick()


def checkForQuit():
    for event in pygame.event.get(QUIT): # Evênements pour quitter
        terminate() # Fin si un évênement pour quitter est utilisé
    for event in pygame.event.get(KEYUP):  # Tous les évênements possibles
        if event.key == K_ESCAPE:
            terminate() # Fin si la touche est Esc
        pygame.event.post(event) 


def calculateLevelAndFallFreq(score,level):
    # Fréquence de chute calculée selon le niveau du joueur qui lui même est calculé selon son score
    
    while score > LEVELS[level]:
        level+=1
    fallFreq = 0.50 *(0.9**level)
    return level, fallFreq

def getNewPiece(is_normal=True):
   # Renvoie une pièce aléatoire avec une forme aléatoire et aussi dans une couleur aléatoire
    if is_normal:
        shape = random.choice(list(FORMES_NORMALES))
    else:
        shape = random.choice(list(FORMES_MALUS))

    newPiece = {'shape': shape,
                'rotation': random.randint(0, len(PIECES[shape]) - 1),
                'x': int(BOARDWIDTH / 2) - int(TEMPLATEWIDTH / 2),
                'y': -2, # Commence au dessus du board
                'color': random.randint(0, len(COLORS)-3)} # Les 2 dernieres couleurs sont réservées pour les malus
    return newPiece


def addToBoard(board, piece):
   # Remplissage du terrain de jeu basé sur la localisation de la pièce, sa forme et sa rotation
    for x in range(TEMPLATEWIDTH):
        for y in range(TEMPLATEHEIGHT):
            if PIECES[piece['shape']][piece['rotation']][y][x] != BLANK:
                board[x + piece['x']][y + piece['y']] = piece['color']


def getBlankBoard():
    # Crée et retourne un nouveau terrain de jeu
    board = []
    for i in range(BOARDWIDTH):
        board.append([BLANK] * BOARDHEIGHT)
    return board


def isOnBoard(x, y):
    return x >= 0 and x < BOARDWIDTH and y < BOARDHEIGHT


def isValidPosition(board, piece, adjX=0, adjY=0):
    # Retourne si la pièce est à l'intérieur du terrain et si elle n'est pas rentrée en collision
    for x in range(TEMPLATEWIDTH):
        for y in range(TEMPLATEHEIGHT):
            isAboveBoard = y + piece['y'] + adjY < 0
            if isAboveBoard or PIECES[piece['shape']][piece['rotation']][y][x] == BLANK:
                continue
            if not isOnBoard(x + piece['x'] + adjX, y + piece['y'] + adjY):
                return False
            if board[x + piece['x'] + adjX][y + piece['y'] + adjY] != BLANK:
                return False
    return True

def isCompleteLine(board, y):
    # Retourne True si la ligne a été complétée sans aucun trou
    for x in range(BOARDWIDTH):
        if board[x][y] == BLANK:
            return False
    return True


def removeCompleteLines(board):
    # Retire toutes les lignes complétées sur le terrain, fait tomber celles qui étaient au dessus et renvoie le nombre de lignes complétées à un même instant
    numLinesRemoved = 0
    y = BOARDHEIGHT - 1 # Le y débute en bas du board
    while y >= 0:
        if isCompleteLine(board, y):
            #  Retire la ligne et descend les autres d'une ligne
            for pullDownY in range(y, 0, -1):
                for x in range(BOARDWIDTH):
                    board[x][pullDownY] = board[x][pullDownY-1]
            # Mise en place de la première ligne vide
            for x in range(BOARDWIDTH):
                board[x][0] = BLANK
            numLinesRemoved += 1
        else:
            y -= 1 # Check la prochaine ligne
    return numLinesRemoved


def convertToPixelCoords(boxx, boxy,is_right):
    # Transforme les coordonnées données par rapport au  board en coordonnées xy par rapport à la fenêtre
    # Coordonnées de l'emplacement sur l'écran
    if is_right:
        return (XMARGIN + (boxx * BOXSIZE)), (TOPMARGIN + (boxy * BOXSIZE))
    else:
        return (XMARGIN2 + (boxx * BOXSIZE)), (TOPMARGIN + (boxy * BOXSIZE))

def drawBox(boxx, boxy, color,is_right, pixelx=None, pixely=None):
    # Affiche une box
    # Au coordonnées xy sur le board
    # Si pixelx et pixely sont spécifiés, dessine en fonction des coordonnées stockées dans pixelx et pixely
    # (Utilisée pour la prochaine pièce "Next piece")
    if color == BLANK:
        return
    if pixelx == None and pixely == None:
        pixelx, pixely = convertToPixelCoords(boxx, boxy,is_right)
    pygame.draw.rect(DISPLAYSURF, COLORS[color], (pixelx + 1, pixely + 1, BOXSIZE - 1, BOXSIZE - 1))
    pygame.draw.rect(DISPLAYSURF, LIGHTCOLORS[color], (pixelx + 1, pixely + 1, BOXSIZE - 4, BOXSIZE - 4))


def drawBoard(board,board2):
    # Affiche les boards autour des informations et de la box de chaque joueur
    pygame.draw.rect(DISPLAYSURF, BORDERCOLOR, (XMARGIN-3, TOPMARGIN - 1, (BOARDWIDTH * BOXSIZE) + 5, (BOARDHEIGHT * BOXSIZE) + 5), 5) # Border de la partie gauche
    pygame.draw.rect(DISPLAYSURF, BORDERCOLOR, (XMARGIN2-3, TOPMARGIN - 2, (BOARDWIDTH * BOXSIZE) + 5, (BOARDHEIGHT * BOXSIZE) + 5), 5) # Border de la partie droite
    pygame.draw.rect(DISPLAYSURF, BORDERCOLOR, (XMARGIN3-3, TOPMARGIN2 - 7, (6 * BOXSIZE) + 8, (10  * BOXSIZE) + 8), 5) # Border autour des informations sur la partie du joueur jouant à gauche
    pygame.draw.rect(DISPLAYSURF, BORDERCOLOR, (XMARGIN4-3, TOPMARGIN2 - 7, (6 * BOXSIZE) + 8, (10  * BOXSIZE) + 8), 5) # Border autour des informations sur la partie du joueur jouant à droite


    # Remplit l'arrière plan du board
    pygame.draw.rect(DISPLAYSURF, BGCOLOR, (XMARGIN, TOPMARGIN, BOXSIZE * BOARDWIDTH, BOXSIZE * BOARDHEIGHT))
    
    for i in range(BOARDHEIGHT):
        pygame.draw.line(DISPLAYSURF, LINECOLOR,(XMARGIN,TOPMARGIN+i*BOXSIZE),(XMARGIN+BOARDWIDTH*BOXSIZE,TOPMARGIN+i*BOXSIZE))
        pygame.draw.line(DISPLAYSURF,LINECOLOR,(XMARGIN2,TOPMARGIN+i*BOXSIZE),(XMARGIN2+BOARDWIDTH*BOXSIZE,TOPMARGIN+i*BOXSIZE))
    for j in range (BOARDWIDTH):
        pygame.draw.line(DISPLAYSURF,LINECOLOR,(XMARGIN+j*BOXSIZE,TOPMARGIN+BOARDHEIGHT*BOXSIZE),(XMARGIN+j*BOXSIZE,TOPMARGIN))
        pygame.draw.line(DISPLAYSURF,LINECOLOR,(XMARGIN2+j*BOXSIZE,TOPMARGIN+BOARDHEIGHT*BOXSIZE),(XMARGIN2+j*BOXSIZE,TOPMARGIN))
    # Affiche les boxs individuelles sur le board
    for x in range(BOARDWIDTH):
        for y in range(BOARDHEIGHT):
            drawBox(x, y, board[x][y],True)

    for x in range(BOARDWIDTH):
        for y in range(BOARDHEIGHT):
            drawBox(x, y, board2[x][y],False)


def drawStatus(score, level,score2,level2,totalLigne,totalLigne2):
    #score à droite
    scoreSurf = BASICFONT.render('Score: %s' % score, True, TEXTCOLOR)
    scoreRect = scoreSurf.get_rect()
    scoreRect.topleft = (WINDOWWIDTH - 130, 20)
    DISPLAYSURF.blit(scoreSurf, scoreRect)
    levelSurf = BASICFONT.render('Level: %s' % level, True, TEXTCOLOR)
    levelRect = levelSurf.get_rect()
    levelRect.topleft = (WINDOWWIDTH - 130, 50)
    DISPLAYSURF.blit(levelSurf, levelRect)
    ligneSurf = BASICFONT.render('Lignes: %s' % totalLigne, True, TEXTCOLOR)
    ligneRect = ligneSurf.get_rect()
    ligneRect.topleft = (WINDOWWIDTH - 130, 80)
    DISPLAYSURF.blit(ligneSurf, ligneRect)
    #score à gauche
    scoreSurf2 = BASICFONT.render('Score: %s' % score2, True, TEXTCOLOR)
    scoreRect2 = scoreSurf2.get_rect()
    scoreRect2.topleft = (20, 20)
    DISPLAYSURF.blit(scoreSurf2, scoreRect2)
    levelSurf2 = BASICFONT.render('Level: %s' % level2, True, TEXTCOLOR)
    levelRect2 = levelSurf2.get_rect()
    levelRect2.topleft = (20, 50)
    DISPLAYSURF.blit(levelSurf2, levelRect2)
    ligneSurf2 = BASICFONT.render('Lignes: %s' % totalLigne2, True, TEXTCOLOR)
    ligneRect2 = ligneSurf2.get_rect()
    ligneRect2.topleft = (20, 80)
    DISPLAYSURF.blit(ligneSurf2, ligneRect2)
    
def drawPiece(piece,is_right, pixelx=None, pixely=None):
    shapeToDraw = PIECES[piece['shape']][piece['rotation']]
    if pixelx == None and pixely == None:
        pixelx, pixely = convertToPixelCoords(piece['x'], piece['y'],is_right)

    # Affiche une pièce sur chaque box
    for x in range(TEMPLATEWIDTH):
        for y in range(TEMPLATEHEIGHT):
            if shapeToDraw[y][x] != BLANK:
                drawBox(None, None,piece['color'], is_right, pixelx + (x * BOXSIZE), pixely + (y * BOXSIZE))


def drawNextPiece(piece,is_right):
    # Affiche le texte "next"
    if is_right:
        nextSurf = BASICFONT.render('Next:', True, TEXTCOLOR)
        nextRect = nextSurf.get_rect()
        nextRect.topleft = (WINDOWWIDTH - 130, 110)
        DISPLAYSURF.blit(nextSurf, nextRect)
    # Affiche la "next" piece
        drawPiece(piece,True, pixelx=WINDOWWIDTH-120, pixely=130)
    else:
        nextSurf = BASICFONT.render('Next:', True, TEXTCOLOR)
        nextRect = nextSurf.get_rect()
        nextRect.topleft = (20, 110)
        DISPLAYSURF.blit(nextSurf, nextRect)
    # Affiche la "next" piece
        drawPiece(piece,True, 15, pixely=130)

def getFantomPiece(piece,board):
    fantomPiece={"shape":piece["shape"],
                 "rotation":piece["rotation"],
                 "x":piece["x"],
                 "y":piece["y"],
                 "color":len(COLORS)-1}
    for i in range(1, BOARDHEIGHT):
        if not isValidPosition(board, fantomPiece, adjY=i):
            break
    fantomPiece["y"]+=i-1
    return(fantomPiece)



if __name__ == '__main__':
    main()
