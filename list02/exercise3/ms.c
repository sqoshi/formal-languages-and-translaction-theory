#include "test/*asdf*/header.h"
#include <stdio.h>
#include <foo/*bar*/baz.h>
 /*   , /*!   , ///   i //!*/
/** \brief Java style Doc String - Foo function */
int foo();
/***
*testuje
*/
int bar(); /**< Bar function */
/// Bajldado
// testdata
//! tgtm
/*!
xD*/
/// .NET Style Doc String
int g_global_var = 1;
/* Hello
/* World
// */
int baz();
// */
/**/
/*! Global variable
 *  ... */
volatile int g_global;
//xd

//! Main
int main(int argc, const char* argv)
{
    printf("/* foo bar");
    //*/ bar();

    // \
    /*
    baz();
    /*/
    foo();
    //*/

/\
/*
    baz();
/*/
    foo();
//*/

    return 1;
}
