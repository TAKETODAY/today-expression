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

/* Generated By:JavaCC: Do not edit this line. SimpleCharStream.java Version 5.0 */
/* JavaCCOptions:STATIC=false,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.sun.el.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 * An implementation of interface CharStream, where the stream is assumed to
 * contain only ASCII characters (without unicode processing).
 */
public class SimpleCharStream {

    /** Whether parser is static. */
//	public static final boolean staticFlag = false;
    int bufsize;
    int available;
    int tokenBegin;
    /** Position in buffer. */
    public int bufpos = -1;
    protected int bufline[];
    protected int bufcolumn[];

    protected int column = 0;
    protected int line = 1;

    protected boolean prevCharIsCR = false;
    protected boolean prevCharIsLF = false;

    protected final Reader inputStream;

    protected char[] buffer;
    protected int maxNextCharInd = 0;
    protected int inBuf = 0;
    protected final int tabSize = 4;

    /** Constructor. */
    public SimpleCharStream(Reader dstream, int startline, int startcolumn, int buffersize) {
        inputStream = dstream;
        line = startline;
        column = startcolumn - 1;

        available = bufsize = buffersize;
        buffer = new char[buffersize];
        bufline = new int[buffersize];
        bufcolumn = new int[buffersize];
    }

    public SimpleCharStream(Reader dstream, int startline, int startcolumn) {
        this(dstream, startline, startcolumn, 512);
    }

    public SimpleCharStream(Reader dstream) {
        this(dstream, 1, 1, 512);
    }

    public SimpleCharStream(InputStream dstream, String encoding, int startline, int startcolumn, int buffersize) //
            throws UnsupportedEncodingException //
    {
        this(encoding == null ? new InputStreamReader(dstream)
                : new InputStreamReader(dstream, encoding), startline, startcolumn, buffersize);
    }

    public SimpleCharStream(InputStream dstream, int startline, int startcolumn, int buffersize) {
        this(new InputStreamReader(dstream), startline, startcolumn, buffersize);
    }

    public SimpleCharStream(InputStream dstream, String encoding, int startline, int startcolumn) //
            throws UnsupportedEncodingException ///
    {
        this(dstream, encoding, startline, startcolumn, 512);
    }

    public SimpleCharStream(InputStream dstream, int startline, int startcolumn) {
        this(dstream, startline, startcolumn, 512);
    }

    public SimpleCharStream(InputStream dstream, String encoding) throws UnsupportedEncodingException {
        this(dstream, encoding, 1, 1, 512);
    }

    public SimpleCharStream(InputStream dstream) {
        this(dstream, 1, 1, 512);
    }

    protected void ExpandBuff(boolean wrapAround) {
        char[] newbuffer = new char[bufsize + 2048];
        int newbufline[] = new int[bufsize + 2048];
        int newbufcolumn[] = new int[bufsize + 2048];

        try {
            if (wrapAround) {
                System.arraycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
                System.arraycopy(buffer, 0, newbuffer, bufsize - tokenBegin, bufpos);
                buffer = newbuffer;

                System.arraycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
                System.arraycopy(bufline, 0, newbufline, bufsize - tokenBegin, bufpos);
                bufline = newbufline;

                System.arraycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
                System.arraycopy(bufcolumn, 0, newbufcolumn, bufsize - tokenBegin, bufpos);
                bufcolumn = newbufcolumn;

                maxNextCharInd = (bufpos += (bufsize - tokenBegin));
            }
            else {
                System.arraycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
                buffer = newbuffer;

                System.arraycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
                bufline = newbufline;

                System.arraycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
                bufcolumn = newbufcolumn;

                maxNextCharInd = (bufpos -= tokenBegin);
            }
        }
        catch (Throwable t) {
            throw new Error(t.getMessage());
        }

        bufsize += 2048;
        available = bufsize;
        tokenBegin = 0;
    }

    protected void FillBuff() throws IOException {
        if (maxNextCharInd == available) {
            if (available == bufsize) {
                if (tokenBegin > 2048) {
                    bufpos = maxNextCharInd = 0;
                    available = tokenBegin;
                }
                else if (tokenBegin < 0)
                    bufpos = maxNextCharInd = 0;
                else
                    ExpandBuff(false);
            }
            else if (available > tokenBegin)
                available = bufsize;
            else if ((tokenBegin - available) < 2048)
                ExpandBuff(true);
            else
                available = tokenBegin;
        }

        int i;
        try {
            if ((i = inputStream.read(buffer, maxNextCharInd, available - maxNextCharInd)) == -1) {
                inputStream.close();
                throw new IOException();
            }
            else
                maxNextCharInd += i;
            return;
        }
        catch (IOException e) {
            --bufpos;
            backup(0);
            if (tokenBegin == -1)
                tokenBegin = bufpos;
            throw e;
        }
    }

    /** Start. */
    public char BeginToken() throws IOException {
        tokenBegin = -1;
        char c = readChar();
        tokenBegin = bufpos;

        return c;
    }

    protected void UpdateLineColumn(char c) {
        column++;

        if (prevCharIsLF) {
            prevCharIsLF = false;
            line += (column = 1);
        }
        else if (prevCharIsCR) {
            prevCharIsCR = false;
            if (c == '\n') {
                prevCharIsLF = true;
            }
            else
                line += (column = 1);
        }

        switch (c)
        {
            case '\r' :
                prevCharIsCR = true;
                break;
            case '\n' :
                prevCharIsLF = true;
                break;
            case '\t' :
                column--;
                column += (tabSize - (column % tabSize));
                break;
            default:
                break;
        }

        bufline[bufpos] = line;
        bufcolumn[bufpos] = column;
    }

    /** Read a character. */
    public char readChar() throws IOException {
        if (inBuf > 0) {
            --inBuf;

            if (++bufpos == bufsize)
                bufpos = 0;

            return buffer[bufpos];
        }

        if (++bufpos >= maxNextCharInd)
            FillBuff();

        char c = buffer[bufpos];

        UpdateLineColumn(c);
        return c;
    }

    @Deprecated
    /**
     * @deprecated
     * @see #getEndColumn
     */
    public int getColumn() {
        return bufcolumn[bufpos];
    }

    @Deprecated
    /**
     * @deprecated
     * @see #getEndLine
     */
    public int getLine() {
        return bufline[bufpos];
    }

    /** Get token end column number. */
    public int getEndColumn() {
        return bufcolumn[bufpos];
    }

    /** Get token end line number. */
    public int getEndLine() {
        return bufline[bufpos];
    }

    /** Get token beginning column number. */
    public int getBeginColumn() {
        return bufcolumn[tokenBegin];
    }

    /** Get token beginning line number. */
    public int getBeginLine() {
        return bufline[tokenBegin];
    }

    /** Backup a number of characters. */
    public void backup(int amount) {

        inBuf += amount;
        if ((bufpos -= amount) < 0)
            bufpos += bufsize;
    }

    /** Get token literal value. */
    public String GetImage() {
        if (bufpos >= tokenBegin) {
            return new String(buffer, tokenBegin, bufpos - tokenBegin + 1);
        }
        return new String(buffer, tokenBegin, bufsize - tokenBegin) + new String(buffer, 0, bufpos + 1);
    }

    /** Get the suffix. */
    public char[] GetSuffix(int len) {
        char[] ret = new char[len];

        if ((bufpos + 1) >= len)
            System.arraycopy(buffer, bufpos - len + 1, ret, 0, len);
        else {
            System.arraycopy(buffer, bufsize - (len - bufpos - 1), ret, 0,
                    len - bufpos - 1);
            System.arraycopy(buffer, 0, ret, len - bufpos - 1, bufpos + 1);
        }
        return ret;
    }

    /** Reset buffer when finished. */
//	public void Done() {
//		buffer = null;
//		bufline = null;
//		bufcolumn = null;
//	}

    /**
     * Method to adjust line and column numbers for the start of a token.
     */
    public void adjustBeginLineColumn(int newLine, int newCol) {
        int start = tokenBegin;
        int len;

        if (bufpos >= tokenBegin) {
            len = bufpos - tokenBegin + inBuf + 1;
        }
        else {
            len = bufsize - tokenBegin + bufpos + 1 + inBuf;
        }

        int i = 0, j = 0, k = 0;
        int nextColDiff = 0, columnDiff = 0;

        while (i < len && bufline[j = start % bufsize] == bufline[k = ++start % bufsize]) {
            bufline[j] = newLine;
            nextColDiff = columnDiff + bufcolumn[k] - bufcolumn[j];
            bufcolumn[j] = newCol + columnDiff;
            columnDiff = nextColDiff;
            i++;
        }

        if (i < len) {
            bufline[j] = newLine++;
            bufcolumn[j] = newCol + columnDiff;

            while (i++ < len) {
                if (bufline[j = start % bufsize] != bufline[++start % bufsize])
                    bufline[j] = newLine++;
                else
                    bufline[j] = newLine;
            }
        }

        line = bufline[j];
        column = bufcolumn[j];
    }

}
/*
 * JavaCC - OriginalChecksum=7ea14199259e7ce0336b228c8cdb9958 (do not edit this
 * line)
 */
