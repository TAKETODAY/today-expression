/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://oss.oracle.com/licenses/CDDL+GPL-1.1
 * or LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

/* Generated By:JJTree&JavaCC: Do not edit this line. ELParserConstants.java */
package com.sun.el.parser;

/**
 * Token literal values and constants. Generated by
 * org.javacc.parser.OtherFilesGen#start()
 */
public interface ELParserConstants {

	/** End of File. */
	int EOF = 0;
	/** RegularExpression Id. */
	int LITERAL_EXPRESSION = 1;
	/** RegularExpression Id. */
	int START_DYNAMIC_EXPRESSION = 2;
	/** RegularExpression Id. */
	int START_DEFERRED_EXPRESSION = 3;
	/** RegularExpression Id. */
	int START_MAP = 9;
	/** RegularExpression Id. */
	int RCURL = 10;
	/** RegularExpression Id. */
	int INTEGER_LITERAL = 11;
	/** RegularExpression Id. */
	int FLOATING_POINT_LITERAL = 12;
	/** RegularExpression Id. */
	int EXPONENT = 13;
	/** RegularExpression Id. */
	int STRING_LITERAL = 14;
	/** RegularExpression Id. */
	int BADLY_ESCAPED_STRING_LITERAL = 15;
	/** RegularExpression Id. */
	int TRUE = 16;
	/** RegularExpression Id. */
	int FALSE = 17;
	/** RegularExpression Id. */
	int NULL = 18;
	/** RegularExpression Id. */
	int DOT = 19;
	/** RegularExpression Id. */
	int LPAREN = 20;
	/** RegularExpression Id. */
	int RPAREN = 21;
	/** RegularExpression Id. */
	int LBRACK = 22;
	/** RegularExpression Id. */
	int RBRACK = 23;
	/** RegularExpression Id. */
	int COLON = 24;
	/** RegularExpression Id. */
	int COMMA = 25;
	/** RegularExpression Id. */
	int SEMICOLON = 26;
	/** RegularExpression Id. */
	int GT0 = 27;
	/** RegularExpression Id. */
	int GT1 = 28;
	/** RegularExpression Id. */
	int LT0 = 29;
	/** RegularExpression Id. */
	int LT1 = 30;
	/** RegularExpression Id. */
	int GE0 = 31;
	/** RegularExpression Id. */
	int GE1 = 32;
	/** RegularExpression Id. */
	int LE0 = 33;
	/** RegularExpression Id. */
	int LE1 = 34;
	/** RegularExpression Id. */
	int EQ0 = 35;
	/** RegularExpression Id. */
	int EQ1 = 36;
	/** RegularExpression Id. */
	int NE0 = 37;
	/** RegularExpression Id. */
	int NE1 = 38;
	/** RegularExpression Id. */
	int NOT0 = 39;
	/** RegularExpression Id. */
	int NOT1 = 40;
	/** RegularExpression Id. */
	int AND0 = 41;
	/** RegularExpression Id. */
	int AND1 = 42;
	/** RegularExpression Id. */
	int OR0 = 43;
	/** RegularExpression Id. */
	int OR1 = 44;
	/** RegularExpression Id. */
	int EMPTY = 45;
	/** RegularExpression Id. */
	int INSTANCEOF = 46;
	/** RegularExpression Id. */
	int MULT = 47;
	/** RegularExpression Id. */
	int PLUS = 48;
	/** RegularExpression Id. */
	int MINUS = 49;
	/** RegularExpression Id. */
	int QUESTIONMARK = 50;
	/** RegularExpression Id. */
	int DIV0 = 51;
	/** RegularExpression Id. */
	int DIV1 = 52;
	/** RegularExpression Id. */
	int MOD0 = 53;
	/** RegularExpression Id. */
	int MOD1 = 54;
	/** RegularExpression Id. */
	int CONCAT = 55;
	/** RegularExpression Id. */
	int ASSIGN = 56;
	/** RegularExpression Id. */
	int ARROW = 57;
	/** RegularExpression Id. */
	int IDENTIFIER = 58;
	/** RegularExpression Id. */
	int IMPL_OBJ_START = 59;
	/** RegularExpression Id. */
	int LETTER = 60;
	/** RegularExpression Id. */
	int DIGIT = 61;
	/** RegularExpression Id. */
	int ILLEGAL_CHARACTER = 62;

	/** Lexical state. */
	int DEFAULT = 0;
	/** Lexical state. */
	int IN_EXPRESSION = 1;
	/** Lexical state. */
	int IN_MAP = 2;

	/** Literal token values. */
	String[] tokenImage = { "<EOF>", "<LITERAL_EXPRESSION>", "\"${\"", "\"#{\"", "\"\\\\\"", "\" \"", "\"\\t\"", "\"\\n\"", "\"\\r\"", "\"{\"", "\"}\"", "<INTEGER_LITERAL>", "<FLOATING_POINT_LITERAL>", "<EXPONENT>", "<STRING_LITERAL>", "<BADLY_ESCAPED_STRING_LITERAL>", "\"true\"", "\"false\"", "\"null\"", "\".\"", "\"(\"", "\")\"", "\"[\"", "\"]\"", "\":\"", "\",\"", "\";\"", "\">\"", "\"gt\"", "\"<\"", "\"lt\"", "\">=\"", "\"ge\"", "\"<=\"", "\"le\"", "\"==\"", "\"eq\"", "\"!=\"", "\"ne\"", "\"!\"", "\"not\"", "\"&&\"", "\"and\"", "\"||\"", "\"or\"", "\"empty\"", "\"instanceof\"", "\"*\"", "\"+\"", "\"-\"", "\"?\"", "\"/\"", "\"div\"", "\"%\"", "\"mod\"", "\"+=\"", "\"=\"", "\"->\"", "<IDENTIFIER>", "\"#\"", "<LETTER>", "<DIGIT>", "<ILLEGAL_CHARACTER>",
	};

}
