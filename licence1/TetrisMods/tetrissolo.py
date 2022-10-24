import random, time, pygame, sys
from pygame.locals import *

FPS = 25
WINDOWWIDTH = 640
WINDOWHEIGHT = 480
BOXSIZE = 20
BOARDWIDTH = 10
BOARDHEIGHT = 20
BLANK = '.'

MOVESIDEWAYSFREQ = 0.08
MOVEDOWNFREQ = 0.1
XMARGIN = int((WINDOWWIDTH - BOARDWIDTH * BOXSIZE) / 2)
TOPMARGIN = WINDOWHEIGHT - (BOARDHEIGHT * BOXSIZE) - 5

#               R    G    B
BLANC       = (255, 255, 255)
GRIS        = (185, 185, 185)
NOIR        = (  0,   0,   0)
ROUGE       = (155,   0,   0)
ROUGECLAIR  = (175,  20,  20)
VERT        = (  0, 155,   0)
VERTCLAIR   = ( 20, 175,  20)
BLEU        = (  0, 39, 186 )
BLEUCLAIR   = ( 20,  59, 205)
JAUNE      = (193, 196,   0)
JAUNECLAIR = (213, 216,  20)

LINECOLOR= (100, 96, 99)
BORDERCOLOR = GRIS
BGCOLOR = NOIR
TEXTCOLOR = BLANC
TEXTSHADOWCOLOR = GRIS
COLORS      = (     BLEU,      VERT,      ROUGE,      JAUNE,    BLANC)
LIGHTCOLORS = (BLEUCLAIR, VERTCLAIR, ROUGECLAIR, JAUNECLAIR,    BLANC)
assert len(COLORS) == len(LIGHTCOLORS) # Chaque couleur est associée a une couleur claire pour pouvoir faire le contour de chaque carré

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

PIECES = {'S': S_SHAPE_TEMPLATE,
          'Z': Z_SHAPE_TEMPLATE,
          'J': J_SHAPE_TEMPLATE,
          'L': L_SHAPE_TEMPLATE,
          'I': I_SHAPE_TEMPLATE,
          'O': O_SHAPE_TEMPLATE,
          'T': T_SHAPE_TEMPLATE}

LEVELS=[]
scoreLevel=0
for i in range(30):
    scoreLevel+=500*i
    LEVELS.append(scoreLevel)


def main():
    global FPSCLOCK, DISPLAYSURF, BASICFONT, BIGFONT,pieceFantomeOn
    pygame.init()
    FPSCLOCK = pygame.time.Clock()
    DISPLAYSURF = pygame.display.set_mode((WINDOWWIDTH, WINDOWHEIGHT))
    BASICFONT = pygame.font.Font('freesansbold.ttf', 18)
    BIGFONT = pygame.font.Font('freesansbold.ttf', 70)
    pygame.display.set_caption('Tetris')
    accueil()
    pieceFantomeOn=chooseMode()
    while True: 
        runGame()
        showTextScreen('Game Over')


def runGame():
    # initialisation des variables
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
    level, fallFreq = calculateLevelAndFallFreq(score,level)

    fallingPiece = getNewPiece()
    nextPiece = getNewPiece()

    while True: 
        if fallingPiece == None: 
            fallingPiece = nextPiece
            nextPiece = getNewPiece()
            lastFallTime = time.time() # on reset le lastFallTime

            if not isValidPosition(board, fallingPiece):
                return # Si on ne peut ajouter la nouvelle piece --> game over

        checkForQuit()
        for event in pygame.event.get(): # boucle sur les évenements
            if event.type == KEYUP:
                if (event.key == K_p):
                    # Pause du jeu
                    DISPLAYSURF.fill(BGCOLOR)
                    showTextScreen('Paused') # pause jusqu'à ce qu'on appuie sur une touche
                    lastFallTime = time.time()
                    lastMoveDownTime = time.time()
                    lastMoveSidewaysTime = time.time()
                elif (event.key == K_LEFT or event.key == K_a):
                    movingLeft = False
                elif (event.key == K_RIGHT or event.key == K_d):
                    movingRight = False
                elif (event.key == K_DOWN or event.key == K_s):
                    movingDown = False

            elif event.type == KEYDOWN:
                # bouger la pièce sur les cotés
                if (event.key == K_LEFT or event.key == K_a) and isValidPosition(board, fallingPiece, adjX=-1):
                    fallingPiece['x'] -= 1
                    movingLeft = True
                    movingRight = False
                    lastMoveSidewaysTime = time.time()

                elif (event.key == K_RIGHT or event.key == K_d) and isValidPosition(board, fallingPiece, adjX=1):
                    fallingPiece['x'] += 1
                    movingRight = True
                    movingLeft = False
                    lastMoveSidewaysTime = time.time()

                # rotation de la pièce dans un sens
                elif (event.key == K_UP or event.key == K_w):
                    fallingPiece['rotation'] = (fallingPiece['rotation'] + 1) % len(PIECES[fallingPiece['shape']])
                    if not isValidPosition(board, fallingPiece):
                        fallingPiece['rotation'] = (fallingPiece['rotation'] - 1) % len(PIECES[fallingPiece['shape']])
                elif (event.key == K_q): # dans l'autre sens
                    fallingPiece['rotation'] = (fallingPiece['rotation'] - 1) % len(PIECES[fallingPiece['shape']])
                    if not isValidPosition(board, fallingPiece):
                        fallingPiece['rotation'] = (fallingPiece['rotation'] + 1) % len(PIECES[fallingPiece['shape']])

                # accelere la chute
                elif (event.key == K_DOWN or event.key == K_s):
                    movingDown = True
                    if isValidPosition(board, fallingPiece, adjY=1):
                        fallingPiece['y'] += 1
                    lastMoveDownTime = time.time()

                # fais tomber la pièce au plus bas possible
                elif event.key == K_SPACE:
                    movingDown = False
                    movingLeft = False
                    movingRight = False
                    for i in range(1, BOARDHEIGHT):
                        if not isValidPosition(board, fallingPiece, adjY=i):
                            break
                    fallingPiece['y'] += i - 1
                    lastFallTime=fallFreq
                    
        

        if (movingLeft or movingRight) and time.time() - lastMoveSidewaysTime > MOVESIDEWAYSFREQ:
            if movingLeft and isValidPosition(board, fallingPiece, adjX=-1):
                fallingPiece['x'] -= 1
            elif movingRight and isValidPosition(board, fallingPiece, adjX=1):
                fallingPiece['x'] += 1
            lastMoveSidewaysTime = time.time()

        if movingDown and time.time() - lastMoveDownTime > MOVEDOWNFREQ and isValidPosition(board, fallingPiece, adjY=1):
            fallingPiece['y'] += 1
            lastMoveDownTime = time.time()

        # faire tomber la pièce si cela fait assez de temps
        if time.time() - lastFallTime > fallFreq:
            # regarde si la pièce a atteri
            if not isValidPosition(board, fallingPiece, adjY=1):
                # elle a atteri , on l'ajoute au board
                addToBoard(board, fallingPiece)
                ligneCompleted=removeCompleteLines(board)
                if ligneCompleted==1:
                    score+=50*level
                if ligneCompleted==2:
                    score+=150*level
                if ligneCompleted==3:
                    score+=350*level
                if ligneCompleted==4:
                    score+=1000*level
                totalLigne+=ligneCompleted
                score+=10*level
                level, fallFreq = calculateLevelAndFallFreq(score,level)
                fallingPiece = None
            else:
                # elle n'a pas atteri, on la fait descendre une fois
                fallingPiece['y'] += 1
                lastFallTime = time.time()

        # affichage sur l'écran
        DISPLAYSURF.fill(BGCOLOR)
        drawBoard(board)
        drawStatus(score, level,totalLigne)
        drawNextPiece(nextPiece)
        if fallingPiece != None:
            if pieceFantomeOn:
                fantomPiece=getFantomPiece(fallingPiece,board)
                drawPiece(fantomPiece)
            drawPiece(fallingPiece)

        pygame.display.update()
        FPSCLOCK.tick(FPS)

    
def chooseMode():
    DISPLAYSURF.blit(pygame.image.load("image/fantomesolo.jpg"),(0,0))
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
    DISPLAYSURF.blit(pygame.image.load("image/commande solo 5.jpg"),(-20,0))
    
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
        if event.type == QUIT:      
            terminate()
        elif event.type == KEYDOWN:
            if event.key == K_ESCAPE:   
                terminate()
            else:
                return event.key   #retourne la touche appuyée   
    return None





def showTextScreen(text):
    # Texte affiché au centre de l'accueil tant qu'aucune touche n'a été actionnée
    # Ombre du texte
    titleSurf, titleRect = makeTextObjs(text, BIGFONT, TEXTSHADOWCOLOR)
    titleRect.center = (int(WINDOWWIDTH / 2), int(WINDOWHEIGHT / 2))
    DISPLAYSURF.blit(titleSurf, titleRect)

    # Affichage the text
    titleSurf, titleRect = makeTextObjs(text, BIGFONT, TEXTCOLOR)
    titleRect.center = (int(WINDOWWIDTH / 2) - 3, int(WINDOWHEIGHT / 2) - 3)
    DISPLAYSURF.blit(titleSurf, titleRect)

    # Affichage du "Press a key to play." 
    pressKeySurf, pressKeyRect = makeTextObjs(' ', BASICFONT, TEXTCOLOR)
    pressKeyRect.center = (int(WINDOWWIDTH / 2), int(WINDOWHEIGHT / 2) + 100)
    DISPLAYSURF.blit(pressKeySurf, pressKeyRect)

    while checkForKeyPress() == None:
        pygame.display.update()
        FPSCLOCK.tick()


def checkForQuit():
    for event in pygame.event.get(QUIT): # Evênements pour quitter
        terminate() # Fin si un évênement pour quitter est utilisé
    for event in pygame.event.get(KEYUP): # Tous les évênements possibles
        if event.key == K_ESCAPE:
            terminate() # Fin si la touche est Esc
        pygame.event.post(event) # remet les autres event KEYUP


def calculateLevelAndFallFreq(score,level):
    # Fréquence de chute calculée selon le niveau du joueur qui lui même est calculé selon son score
    while score > LEVELS[level]:
        level+=1
    fallFreq = 0.50 *(0.9**level)
    return level, fallFreq

def getNewPiece():
    # Renvoie une pièce aléatoire avec une forme aléatoire et aussi dans une couleur aléatoire
    shape = random.choice(list(PIECES.keys()))
    newPiece = {'shape': shape,
                'rotation': random.randint(0, len(PIECES[shape]) - 1),
                'x': int(BOARDWIDTH / 2) - int(TEMPLATEWIDTH / 2),
                'y': -2, # Commence au dessus du board
                'color': random.randint(0, len(COLORS)-2)} # la derniere couleur est réservée pour la pièce fantome
    return newPiece

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
    


def addToBoard(board, piece):
    # ajoute au board selon la position de la piece,sa "shape", sa rotation
    for x in range(TEMPLATEWIDTH):
        for y in range(TEMPLATEHEIGHT):
            if PIECES[piece['shape']][piece['rotation']][y][x] != BLANK:
                board[x + piece['x']][y + piece['y']] = piece['color']


def getBlankBoard():
    # crée et retourne un nouveau board vide
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
            # Retire la ligne et descend les autres d'une ligne
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


def convertToPixelCoords(boxx, boxy):
    # Transforme les coordonnées données par rapport au  board en coordonnées xy par rapport à la fenêtre
    # Coordonnées de l'emplacement sur l'écran
    return (XMARGIN + (boxx * BOXSIZE)), (TOPMARGIN + (boxy * BOXSIZE))


def drawBox(boxx, boxy, color, pixelx=None, pixely=None):
 # Affiche une box
    # Au coordonnées xy sur le board
    # Si pixelx et pixely sont spécifiés, dessine en fonction des coordonnées stockées dans pixelx et pixely
    # (Utilisée pour la prochaine pièce "Next piece")
    if color == BLANK:
        return
    if pixelx == None and pixely == None:
        pixelx, pixely = convertToPixelCoords(boxx, boxy)
    pygame.draw.rect(DISPLAYSURF, COLORS[color], (pixelx + 1, pixely + 1, BOXSIZE - 1, BOXSIZE - 1))
    pygame.draw.rect(DISPLAYSURF, LIGHTCOLORS[color], (pixelx + 1, pixely + 1, BOXSIZE - 4, BOXSIZE - 4))


def drawBoard(board):
    # Affiche les boards autour des informations et de la box de chaque joueur
    pygame.draw.rect(DISPLAYSURF, BORDERCOLOR, (XMARGIN-3, TOPMARGIN - 1, (BOARDWIDTH * BOXSIZE) + 5, (BOARDHEIGHT * BOXSIZE) + 5), 5)

    pygame.draw.rect(DISPLAYSURF, BORDERCOLOR, (WINDOWWIDTH-135,5, 130, 210), 5)

    # Remplit l'arrière plan du board
    pygame.draw.rect(DISPLAYSURF, BGCOLOR, (XMARGIN, TOPMARGIN, BOXSIZE * BOARDWIDTH, BOXSIZE * BOARDHEIGHT))

    for i in range(BOARDHEIGHT):
        pygame.draw.line(DISPLAYSURF, LINECOLOR,(XMARGIN,TOPMARGIN+i*BOXSIZE),(XMARGIN+BOARDWIDTH*BOXSIZE,TOPMARGIN+i*BOXSIZE))
    for j in range (BOARDWIDTH):
        pygame.draw.line(DISPLAYSURF,LINECOLOR,(XMARGIN+j*BOXSIZE,TOPMARGIN+BOARDHEIGHT*BOXSIZE),(XMARGIN+j*BOXSIZE,TOPMARGIN))
    # Affiche les boxs individuelles sur le board
    for x in range(BOARDWIDTH):
        for y in range(BOARDHEIGHT):
            drawBox(x, y, board[x][y])


def drawStatus(score, level,totalLigne):
   
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


def drawPiece(piece, pixelx=None, pixely=None):
    shapeToDraw = PIECES[piece['shape']][piece['rotation']]
    if pixelx == None and pixely == None:
        pixelx, pixely = convertToPixelCoords(piece['x'], piece['y'])

    # Affiche une pièce sur chaque box
    for x in range(TEMPLATEWIDTH):
        for y in range(TEMPLATEHEIGHT):
            if shapeToDraw[y][x] != BLANK:
                drawBox(None, None, piece['color'], pixelx + (x * BOXSIZE), pixely + (y * BOXSIZE))


def drawNextPiece(piece):
    # Affiche le texte "next"
    nextSurf = BASICFONT.render('Next:', True, TEXTCOLOR)
    nextRect = nextSurf.get_rect()
    nextRect.topleft = (WINDOWWIDTH - 130, 110)
    DISPLAYSURF.blit(nextSurf, nextRect)
    # draw the "next" piece
    drawPiece(piece, pixelx=WINDOWWIDTH-120, pixely=120)


if __name__ == '__main__':
    main()
