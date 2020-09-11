//
//  Console.m
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import "Console.h"

@implementation Console

- (void)put:(NSString *)message {
    NSString *messageWithNewline = [NSString stringWithFormat:@"%@\n", message];
    NSData *data = [messageWithNewline dataUsingEncoding:NSUTF8StringEncoding];
    NSFileHandle *handle = [NSFileHandle fileHandleWithStandardOutput];
    [handle writeData:data];
}

-(NSString *)read {
    NSFileHandle *handle = [NSFileHandle fileHandleWithStandardInput];
    NSData *data = handle.availableData;
    NSString *input = [[NSString alloc]initWithData:data encoding:NSUTF8StringEncoding];
    NSCharacterSet *set = [NSCharacterSet newlineCharacterSet];
    NSString *userInput = [input stringByTrimmingCharactersInSet:set];
    
    return userInput;
}

@end
