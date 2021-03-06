from typing import Callable

from game import GameFactory, Game
from player import InputPlayer


def tennis_game(
        write_to_console: Callable[[str], None],
        read_from_console: Callable[[], str],
        parse_input_player: Callable[[str], InputPlayer],
        read_player: Callable[[Callable[[str], None], Callable[[], str], Callable[[str], InputPlayer]], InputPlayer],
        calculate_game_score: Callable[[Game, InputPlayer], Game],
        print_game: Callable[[Callable[[str], None], Callable[[Game], str], Game], None],
        convert_game_score: Callable[[Game], str]) -> None:
    write_to_console("Welcome to the Tennis Game!")
    game = GameFactory.make()
    while not game.completed():
        game = calculate_game_score(game, read_player(write_to_console, read_from_console, parse_input_player))
        print_game(write_to_console, convert_game_score, game)
