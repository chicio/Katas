from Game import Game
from Player import Player
from Score import Score


class GameFactory:
    @staticmethod
    def make() -> Game:
        return Game(Player(Score.Love), Player(Score.Love))

    @staticmethod
    def make_using(score1: Score, score2: Score) -> Game:
        return Game(Player(score1), Player(score2))
