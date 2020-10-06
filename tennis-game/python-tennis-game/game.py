from typing import Callable

from player import Player, InputPlayer
from score import Score


class Game:
    def __init__(self, player1: Player, player2: Player):
        self.player1 = player1
        self.player2 = player2

    def completed(self) -> bool:
        return self.player1.score == Score.Wins or self.player1.score == Score.Wins

    def __eq__(self, other):
        if not isinstance(other, Game):
            return False

        return self.player1 == other.player1 and self.player2 == other.player2


class GameFactory:
    @staticmethod
    def make() -> Game:
        return Game(Player(Score.Love), Player(Score.Love))

    @staticmethod
    def make_using(score1: Score, score2: Score) -> Game:
        return Game(Player(score1), Player(score2))


def calculate_game_score(game: Game, input_player: InputPlayer) -> Game:
    pass


def print_game(write_to_console: Callable[[str], None], game: Game):
    pass

