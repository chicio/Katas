from typing import Callable

from player import Player, InputPlayer
from score import Score


class Game:
    def __init__(self, player1: Player, player2: Player):
        self.player1 = player1
        self.player2 = player2

    def completed(self) -> bool:
        return self.player1.score == Score.Wins or self.player2.score == Score.Wins

    def deuce(self) -> bool:
        return self.player1.score == Score.Forty and self.player2.score == Score.Forty

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

    @staticmethod
    def make_using_inverted(score2: Score, score1: Score) -> Game:
        return Game(Player(score1), Player(score2))


def calculate_game_score(game: Game, input_player: InputPlayer) -> Game:
    if input_player == InputPlayer.Player1:
        return calculate_game_score_using(game.player1, game.player2, GameFactory.make_using)

    if input_player == InputPlayer.Player2:
        return calculate_game_score_using(game.player2, game.player1, GameFactory.make_using_inverted)

    return game


def calculate_game_score_using(scoring_player: Player, opposite_player: Player, game_factory: Callable[[Score, Score], Game]):
    if scoring_player.score == Score.Love:
        return game_factory(Score.Fifteen, opposite_player.score)
    if scoring_player.score == Score.Fifteen:
        return game_factory(Score.Thirty, opposite_player.score)
    if scoring_player.score == Score.Thirty:
        return game_factory(Score.Forty, opposite_player.score)
    if scoring_player.score == Score.Forty:
        if opposite_player.score == Score.Forty:
            return game_factory(Score.Advantage, Score.Forty)
        else:
            if opposite_player.score == Score.Advantage:
                return game_factory(Score.Forty, Score.Forty)
            else:
                return game_factory(Score.Wins, opposite_player.score)
    if scoring_player.score == Score.Advantage:
        return game_factory(Score.Wins, opposite_player.score)

    return game_factory(scoring_player.score, opposite_player.score)


def print_game(write_to_console: Callable[[str], None], convert_score: Callable[[Game], str], game: Game):
    write_to_console(convert_score(game))


def convert_game_score(game: Game) -> str:
    if game.deuce():
        return "Deuce"

    if game.player1.score == Score.Wins:
        return"Player 1 wins"

    if game.player2.score == Score.Wins:
        return "Player 2 wins"

    return "Player 1 {} - Player 2 {}".format(game.player1.score, game.player2.score)
