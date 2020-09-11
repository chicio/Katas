//
//  main.m
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "TennisGameFactory.h"

int main(int argc, const char * argv[]) {
    @autoreleasepool {
        [[TennisGameFactory make] start];
    }
    return 0;
}
