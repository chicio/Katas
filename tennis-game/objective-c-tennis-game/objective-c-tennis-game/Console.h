//
//  Console.h
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface Console : NSObject

- (void)put:(NSString *)message;

- (NSString *)read;

@end

NS_ASSUME_NONNULL_END
