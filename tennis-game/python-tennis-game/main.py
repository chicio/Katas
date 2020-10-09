from console import write_to_console, read_from_console
from game import calculate_game_score, print_game, convert_game_score
from player import parse_input_player, read_player
from tennis_game import tennis_game


if __name__ == '__main__':
    tennis_game(write_to_console,
                read_from_console,
                parse_input_player,
                read_player,
                calculate_game_score,
                print_game,
                convert_game_score)
