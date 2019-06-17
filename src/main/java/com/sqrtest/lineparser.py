#!/usr/bin/env python
# coding: utf-8


import re

SYMBOLS = {'open_parenthesis': '(', 'close_parenthesis': ')', 'hash': '#', 'hyphen': '-', 'open_brances': '{', 'close_brances': '}', 'underscore': '_', 'single_quote': '', 'plus': '+',  'equal_to': '=' }


def replace_substring(string, replacable_str, replacing_to):
    return string.replace(replacable_str, replacing_to)
 
def split_string(string, split_arg):
    return string.split(split_arg)
 
def match_variables(expr):
    return re.match('^[@#\$]', expr)

def get_var_value(variable):
    variable = variable.strip()
    result_variable = variable
    if variable.startswith("'") and variable.endswith("'"):
        result_variable = variable
        if variable[1] == '{' and variable[-2] == '}':
            result_variable = re.sub('[{}]', '', variable)
            result_variable = "str(variables_dictionary['{0}'])".format(result_variable.upper().strip('\''))
    if variable.startswith("{") and variable.endswith("}"):
            result_variable = re.sub('[\'{}]', '', variable)
            result_variable = "variables_dictionary['{0}']".format(result_variable.upper())

    if re.search(r'[@#$][a-zA-Z_0-9]+', variable):
        result_variable = "variables_dictionary['{0}']".format(variable)

    return result_variable


def change_line_variables(if_line):
    var_list = re.findall(r'[@#$&][a-zA-Z_0-9]+|{.*?}|\'.*?\'', if_line)
    for var in var_list:
        if_line = if_line.replace(var, get_var_value(var))
    return if_line

def parse_move(line):
    line = line.strip()
    mv_re = r'move\s+(?P<target>[@#$&][a-zA-Z_0-9]+|{.*?}|\'.*?\')\s+to\s+(?P<source>[@#$&][a-zA-Z_0-9]+|{.*?}|\'.*?\')'
    result = re.search(mv_re, line)
    if result:
        src = result.group('source')
        tar = result.group('target')
        return "{0} = {1}".format(get_var_value(src), get_var_value(tar))
    return line


def parse_do(text):
    text = text.strip().split(' ')[1]

    is_open_parenthesis_exist = SYMBOLS['open_parenthesis'] in text
    parenthesis = SYMBOLS['single_quote'] if is_open_parenthesis_exist else SYMBOLS['open_parenthesis'] + SYMBOLS['close_parenthesis']
    text = replace_substring(text, SYMBOLS['hyphen'], SYMBOLS['underscore'])
    method = text + parenthesis
    return method

def parse_let(line):
    splitted_string = split_string(line, '=')
    variable_name = replace_substring(splitted_string[0].strip(), 'let ', SYMBOLS['single_quote'])
    val_or_expression = replace_substring(splitted_string[1], ' ', SYMBOLS['single_quote'])
    variable_name = get_var_value(variable_name).strip()

    if not (' ' in val_or_expression) and (match_variables(val_or_expression)):
        val_or_expression = get_var_value(val_or_expression)
    let_expression = variable_name+' = '+val_or_expression
    return let_expression

def parse_print(p_line):
    line=p_line.strip()
    output=""
    match = re.search(r'(?i)(print)(.+)(?=\()(\((\+|-)*([0-9]+)*\s*(,)*\s*(\+|-)*([0-9]+)*\))(.*)', line)
    if match:
        matches_ = match.groups()
        if matches_[1].startswith('#') or matches_[1].startswith('$'):
           
            output = "sqr_print(\"" + get_var_value(matches_[1]) + "\",\""+ str(matches_[2]) + "\",\""+ str(matches_[8]) +"\")"
        else:
            output = "sqr_print(" + get_var_value(matches_[1]) + ",\""+ str(matches_[2]) + "\",\""+ str(matches_[8]) +"\")"

    return output
def parse_lines(node):
    if node['command'] in line_switcher:
        return line_switcher[node['command']](node['sqr_txt'])
    return node['sqr_txt']
def parse_add(line):
    #text = split_string(line.strip(), ' ')
    text = line.split()
    source = get_var_value(text[1])
    destination = get_var_value(text[3])
    result = destination + SYMBOLS['equal_to'] + source + SYMBOLS['plus'] + destination
    return result

def parse_comment(line):
    return line.replace('!', '#')

def parse_newline(line):
    return line

def parse_unknown(line):
    return line + "\t#TBD"

def parse_else(line):
    return line

def parse_concat(line):
    text = split_string(line.strip(), ' ')
    first_value = get_var_value(text[1])
    second_value = get_var_value(text[3])
    concat_expression = str(first_value) + " = " +str(first_value) + SYMBOLS['plus'] + str(second_value)
    return concat_expression



def parse_ask(line):
    return ''

def parse_open(line):
    return line

def parse_define(line):
    return ''

def parse_display(line):
    return line

def parse_commit(line):
    return line

def parse_write(line):
    return line

def parse_close(line):
    return line

def parse_lookup(line):
    return line


def parse_else(line):
    return line.strip() + ':'


def parse_load_lookup(line):
    return line

def parse_line(line):
    if line != '':
        key = line.strip().split()[0]
        return line_switcher.get(key, lambda line : "line parsing not available")(line)
    return line
    
line_switcher = {
    "move" : parse_move,
    "do" : parse_do,
    "let" : parse_let,
    "print": parse_print,
    "display": parse_display,
    "open": parse_open,
    "commit": parse_commit,
    "write": parse_write,
    "close": parse_close,
    "concat": parse_concat,
    "lookup": parse_lookup,
    "print": parse_print,
    "add": parse_add,
    'else' : parse_else,
    'comment' : parse_comment,
    'newline' : parse_newline,
    'unknown' : parse_unknown,
    '#define' : parse_define
}
