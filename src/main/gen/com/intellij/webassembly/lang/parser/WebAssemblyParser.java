// This is a generated file. Not intended for manual editing.
package com.intellij.webassembly.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.intellij.webassembly.lang.psi.WebAssemblyTypes.*;
import static com.intellij.webassembly.lang.parser.WebAssemblyParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class WebAssemblyParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    parseLight(root_, builder_);
    return builder_.getTreeBuilt();
  }

  public void parseLight(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    result_ = parse_root_(root_, builder_);
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType root_, PsiBuilder builder_) {
    return parse_root_(root_, builder_, 0);
  }

  static boolean parse_root_(IElementType root_, PsiBuilder builder_, int level_) {
    return webAssemblyFile(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // ALIGNEQKEY UNSIGNED
  public static boolean aligneq(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "aligneq")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ALIGNEQ, "<aligneq>");
    result_ = consumeTokens(builder_, 1, ALIGNEQKEY, UNSIGNED);
    pinned_ = result_; // pin = 1
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::aligneq_recover_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // !(instr | RPAR | <<eof>>)
  static boolean aligneq_recover_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "aligneq_recover_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !aligneq_recover__0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // instr | RPAR | <<eof>>
  private static boolean aligneq_recover__0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "aligneq_recover__0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = instr(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, RPAR);
    if (!result_) result_ = eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ARRAYGET idx
  public static boolean array_get_instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "array_get_instr")) return false;
    if (!nextTokenIs(builder_, ARRAYGET)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARRAY_GET_INSTR, null);
    result_ = consumeToken(builder_, ARRAYGET);
    pinned_ = result_; // pin = 1
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // ARRAYLEN
  public static boolean array_len_instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "array_len_instr")) return false;
    if (!nextTokenIs(builder_, ARRAYLEN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ARRAYLEN);
    exit_section_(builder_, marker_, ARRAY_LEN_INSTR, result_);
    return result_;
  }

  /* ********************************************************** */
  // ARRAYNEW idx
  public static boolean array_new_instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "array_new_instr")) return false;
    if (!nextTokenIs(builder_, ARRAYNEW)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARRAY_NEW_INSTR, null);
    result_ = consumeToken(builder_, ARRAYNEW);
    pinned_ = result_; // pin = 1
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // ARRAYSET idx
  public static boolean array_set_instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "array_set_instr")) return false;
    if (!nextTokenIs(builder_, ARRAYSET)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARRAY_SET_INSTR, null);
    result_ = consumeToken(builder_, ARRAYSET);
    pinned_ = result_; // pin = 1
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // LPAR ARRAYKEY (LPAR MUTKEY storagetype RPAR | storagetype) RPAR
  public static boolean arraytype(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arraytype")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARRAYTYPE, null);
    result_ = consumeTokens(builder_, 2, LPAR, ARRAYKEY);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, arraytype_2(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, RPAR) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // LPAR MUTKEY storagetype RPAR | storagetype
  private static boolean arraytype_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arraytype_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = arraytype_2_0(builder_, level_ + 1);
    if (!result_) result_ = storagetype(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LPAR MUTKEY storagetype RPAR
  private static boolean arraytype_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arraytype_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LPAR, MUTKEY);
    result_ = result_ && storagetype(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // blockinstr_block_ | blockinstr_loop_ | blockinstr_if_
  public static boolean blockinstr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BLOCKINSTR, "<blockinstr>");
    result_ = blockinstr_block_(builder_, level_ + 1);
    if (!result_) result_ = blockinstr_loop_(builder_, level_ + 1);
    if (!result_) result_ = blockinstr_if_(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // BLOCKKEY blockinstr_block_aux_ ENDKEY IDENTIFIER?
  static boolean blockinstr_block_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_block_")) return false;
    if (!nextTokenIs(builder_, BLOCKKEY)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, BLOCKKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, blockinstr_block_aux_(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, ENDKEY)) && result_;
    result_ = pinned_ && blockinstr_block__3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // IDENTIFIER?
  private static boolean blockinstr_block__3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_block__3")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER? blocktype? instr*
  static boolean blockinstr_block_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_block_aux_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = blockinstr_block_aux__0(builder_, level_ + 1);
    result_ = result_ && blockinstr_block_aux__1(builder_, level_ + 1);
    result_ = result_ && blockinstr_block_aux__2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, WebAssemblyParser::blockinstr_recover_);
    return result_;
  }

  // IDENTIFIER?
  private static boolean blockinstr_block_aux__0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_block_aux__0")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  // blocktype?
  private static boolean blockinstr_block_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_block_aux__1")) return false;
    blocktype(builder_, level_ + 1);
    return true;
  }

  // instr*
  private static boolean blockinstr_block_aux__2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_block_aux__2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!instr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "blockinstr_block_aux__2", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // foldeinstr* IFKEY blockinstr_if_aux_ ENDKEY IDENTIFIER?
  static boolean blockinstr_if_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_if_")) return false;
    if (!nextTokenIs(builder_, "", IFKEY, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = blockinstr_if__0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IFKEY);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, blockinstr_if_aux_(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, ENDKEY)) && result_;
    result_ = pinned_ && blockinstr_if__4(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // foldeinstr*
  private static boolean blockinstr_if__0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_if__0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!foldeinstr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "blockinstr_if__0", pos_)) break;
    }
    return true;
  }

  // IDENTIFIER?
  private static boolean blockinstr_if__4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_if__4")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER? blocktype? instr* (ELSEKEY IDENTIFIER? instr*)?
  static boolean blockinstr_if_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_if_aux_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = blockinstr_if_aux__0(builder_, level_ + 1);
    result_ = result_ && blockinstr_if_aux__1(builder_, level_ + 1);
    result_ = result_ && blockinstr_if_aux__2(builder_, level_ + 1);
    result_ = result_ && blockinstr_if_aux__3(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, WebAssemblyParser::blockinstr_recover_);
    return result_;
  }

  // IDENTIFIER?
  private static boolean blockinstr_if_aux__0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_if_aux__0")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  // blocktype?
  private static boolean blockinstr_if_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_if_aux__1")) return false;
    blocktype(builder_, level_ + 1);
    return true;
  }

  // instr*
  private static boolean blockinstr_if_aux__2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_if_aux__2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!instr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "blockinstr_if_aux__2", pos_)) break;
    }
    return true;
  }

  // (ELSEKEY IDENTIFIER? instr*)?
  private static boolean blockinstr_if_aux__3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_if_aux__3")) return false;
    blockinstr_if_aux__3_0(builder_, level_ + 1);
    return true;
  }

  // ELSEKEY IDENTIFIER? instr*
  private static boolean blockinstr_if_aux__3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_if_aux__3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSEKEY);
    result_ = result_ && blockinstr_if_aux__3_0_1(builder_, level_ + 1);
    result_ = result_ && blockinstr_if_aux__3_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER?
  private static boolean blockinstr_if_aux__3_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_if_aux__3_0_1")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  // instr*
  private static boolean blockinstr_if_aux__3_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_if_aux__3_0_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!instr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "blockinstr_if_aux__3_0_2", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // LOOPKEY blockinstr_loop_aux_ ENDKEY IDENTIFIER?
  static boolean blockinstr_loop_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_loop_")) return false;
    if (!nextTokenIs(builder_, LOOPKEY)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, LOOPKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, blockinstr_loop_aux_(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, ENDKEY)) && result_;
    result_ = pinned_ && blockinstr_loop__3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // IDENTIFIER?
  private static boolean blockinstr_loop__3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_loop__3")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER? blocktype? instr*
  static boolean blockinstr_loop_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_loop_aux_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = blockinstr_loop_aux__0(builder_, level_ + 1);
    result_ = result_ && blockinstr_loop_aux__1(builder_, level_ + 1);
    result_ = result_ && blockinstr_loop_aux__2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, WebAssemblyParser::blockinstr_recover_);
    return result_;
  }

  // IDENTIFIER?
  private static boolean blockinstr_loop_aux__0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_loop_aux__0")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  // blocktype?
  private static boolean blockinstr_loop_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_loop_aux__1")) return false;
    blocktype(builder_, level_ + 1);
    return true;
  }

  // instr*
  private static boolean blockinstr_loop_aux__2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_loop_aux__2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!instr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "blockinstr_loop_aux__2", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // !(ENDKEY | <<eof>>)
  static boolean blockinstr_recover_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_recover_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !blockinstr_recover__0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ENDKEY | <<eof>>
  private static boolean blockinstr_recover__0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockinstr_recover__0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ENDKEY);
    if (!result_) result_ = eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // result | typeuse
  public static boolean blocktype(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blocktype")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = result(builder_, level_ + 1);
    if (!result_) result_ = typeuse(builder_, level_ + 1);
    exit_section_(builder_, marker_, BLOCKTYPE, result_);
    return result_;
  }

  /* ********************************************************** */
  // CALLINDIRECTINSTR idx? typeuse?
  public static boolean call_indirect_instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "call_indirect_instr")) return false;
    if (!nextTokenIs(builder_, CALLINDIRECTINSTR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CALL_INDIRECT_INSTR, null);
    result_ = consumeToken(builder_, CALLINDIRECTINSTR);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, call_indirect_instr_1(builder_, level_ + 1));
    result_ = pinned_ && call_indirect_instr_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // idx?
  private static boolean call_indirect_instr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "call_indirect_instr_1")) return false;
    idx(builder_, level_ + 1);
    return true;
  }

  // typeuse?
  private static boolean call_indirect_instr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "call_indirect_instr_2")) return false;
    typeuse(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // CALLINSTR idx
  public static boolean call_instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "call_instr")) return false;
    if (!nextTokenIs(builder_, CALLINSTR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CALL_INSTR, null);
    result_ = consumeToken(builder_, CALLINSTR);
    pinned_ = result_; // pin = 1
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // LINE_COMMENT | BLOCK_COMMENT
  public static boolean comment(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "comment")) return false;
    if (!nextTokenIs(builder_, "<comment>", BLOCK_COMMENT, LINE_COMMENT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMMENT, "<comment>");
    result_ = consumeToken(builder_, LINE_COMMENT);
    if (!result_) result_ = consumeToken(builder_, BLOCK_COMMENT);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // LPAR data_aux_ RPAR
  public static boolean data(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, DATA, null);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && data_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // DATAKEY IDENTIFIER? (memuse_? (instr | LPAR OFFSETKEY instr* RPAR))? STRING*
  static boolean data_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, DATAKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, data_aux__1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, data_aux__2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && data_aux__3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // IDENTIFIER?
  private static boolean data_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_aux__1")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  // (memuse_? (instr | LPAR OFFSETKEY instr* RPAR))?
  private static boolean data_aux__2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_aux__2")) return false;
    data_aux__2_0(builder_, level_ + 1);
    return true;
  }

  // memuse_? (instr | LPAR OFFSETKEY instr* RPAR)
  private static boolean data_aux__2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_aux__2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = data_aux__2_0_0(builder_, level_ + 1);
    result_ = result_ && data_aux__2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // memuse_?
  private static boolean data_aux__2_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_aux__2_0_0")) return false;
    memuse_(builder_, level_ + 1);
    return true;
  }

  // instr | LPAR OFFSETKEY instr* RPAR
  private static boolean data_aux__2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_aux__2_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = instr(builder_, level_ + 1);
    if (!result_) result_ = data_aux__2_0_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LPAR OFFSETKEY instr* RPAR
  private static boolean data_aux__2_0_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_aux__2_0_1_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LPAR, OFFSETKEY);
    result_ = result_ && data_aux__2_0_1_1_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // instr*
  private static boolean data_aux__2_0_1_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_aux__2_0_1_1_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!instr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "data_aux__2_0_1_1_2", pos_)) break;
    }
    return true;
  }

  // STRING*
  private static boolean data_aux__3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "data_aux__3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, STRING)) break;
      if (!empty_element_parsed_guard_(builder_, "data_aux__3", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // LPAR elem_aux_ RPAR
  public static boolean elem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elem")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ELEM, null);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && elem_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // ELEMKEY IDENTIFIER? (
  //                          (LPAR TABLEKEY idx RPAR)? (instr | LPAR OFFSETKEY instr* RPAR)
  //                        | DECLAREKEY
  //                       )? elemlist
  static boolean elem_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elem_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, ELEMKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, elem_aux__1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, elem_aux__2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && elemlist(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // IDENTIFIER?
  private static boolean elem_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elem_aux__1")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  // (
  //                          (LPAR TABLEKEY idx RPAR)? (instr | LPAR OFFSETKEY instr* RPAR)
  //                        | DECLAREKEY
  //                       )?
  private static boolean elem_aux__2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elem_aux__2")) return false;
    elem_aux__2_0(builder_, level_ + 1);
    return true;
  }

  // (LPAR TABLEKEY idx RPAR)? (instr | LPAR OFFSETKEY instr* RPAR)
  //                        | DECLAREKEY
  private static boolean elem_aux__2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elem_aux__2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = elem_aux__2_0_0(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, DECLAREKEY);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (LPAR TABLEKEY idx RPAR)? (instr | LPAR OFFSETKEY instr* RPAR)
  private static boolean elem_aux__2_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elem_aux__2_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = elem_aux__2_0_0_0(builder_, level_ + 1);
    result_ = result_ && elem_aux__2_0_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (LPAR TABLEKEY idx RPAR)?
  private static boolean elem_aux__2_0_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elem_aux__2_0_0_0")) return false;
    elem_aux__2_0_0_0_0(builder_, level_ + 1);
    return true;
  }

  // LPAR TABLEKEY idx RPAR
  private static boolean elem_aux__2_0_0_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elem_aux__2_0_0_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LPAR, TABLEKEY);
    result_ = result_ && idx(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // instr | LPAR OFFSETKEY instr* RPAR
  private static boolean elem_aux__2_0_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elem_aux__2_0_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = instr(builder_, level_ + 1);
    if (!result_) result_ = elem_aux__2_0_0_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LPAR OFFSETKEY instr* RPAR
  private static boolean elem_aux__2_0_0_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elem_aux__2_0_0_1_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LPAR, OFFSETKEY);
    result_ = result_ && elem_aux__2_0_0_1_1_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // instr*
  private static boolean elem_aux__2_0_0_1_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elem_aux__2_0_0_1_1_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!instr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "elem_aux__2_0_0_1_1_2", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ELEMDROPINSTR idx
  public static boolean elem_drop_instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elem_drop_instr")) return false;
    if (!nextTokenIs(builder_, ELEMDROPINSTR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ELEM_DROP_INSTR, null);
    result_ = consumeToken(builder_, ELEMDROPINSTR);
    pinned_ = result_; // pin = 1
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // REFTYPE (instr | LPAR ITEMKEY instr* RPAR)* | FUNCKEY? idx*
  public static boolean elemlist(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elemlist")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ELEMLIST, "<elemlist>");
    result_ = elemlist_0(builder_, level_ + 1);
    if (!result_) result_ = elemlist_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // REFTYPE (instr | LPAR ITEMKEY instr* RPAR)*
  private static boolean elemlist_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elemlist_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, REFTYPE);
    result_ = result_ && elemlist_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (instr | LPAR ITEMKEY instr* RPAR)*
  private static boolean elemlist_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elemlist_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!elemlist_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "elemlist_0_1", pos_)) break;
    }
    return true;
  }

  // instr | LPAR ITEMKEY instr* RPAR
  private static boolean elemlist_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elemlist_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = instr(builder_, level_ + 1);
    if (!result_) result_ = elemlist_0_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LPAR ITEMKEY instr* RPAR
  private static boolean elemlist_0_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elemlist_0_1_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LPAR, ITEMKEY);
    result_ = result_ && elemlist_0_1_0_1_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // instr*
  private static boolean elemlist_0_1_0_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elemlist_0_1_0_1_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!instr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "elemlist_0_1_0_1_2", pos_)) break;
    }
    return true;
  }

  // FUNCKEY? idx*
  private static boolean elemlist_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elemlist_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = elemlist_1_0(builder_, level_ + 1);
    result_ = result_ && elemlist_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // FUNCKEY?
  private static boolean elemlist_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elemlist_1_0")) return false;
    consumeToken(builder_, FUNCKEY);
    return true;
  }

  // idx*
  private static boolean elemlist_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elemlist_1_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!idx(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "elemlist_1_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // LPAR export_aux_ RPAR
  public static boolean export(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "export")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXPORT, null);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && export_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // EXPORTKEY string_aux_ exportdesc
  static boolean export_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "export_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, EXPORTKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, string_aux_(builder_, level_ + 1));
    result_ = pinned_ && exportdesc(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // exportdesc_func_ | exportdesc_table_ | exportdesc_memory_ | exportdesc_global_
  public static boolean exportdesc(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "exportdesc")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = exportdesc_func_(builder_, level_ + 1);
    if (!result_) result_ = exportdesc_table_(builder_, level_ + 1);
    if (!result_) result_ = exportdesc_memory_(builder_, level_ + 1);
    if (!result_) result_ = exportdesc_global_(builder_, level_ + 1);
    exit_section_(builder_, marker_, EXPORTDESC, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPAR exportdesc_func_aux_ RPAR
  static boolean exportdesc_func_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "exportdesc_func_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && exportdesc_func_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // FUNCKEY idx
  static boolean exportdesc_func_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "exportdesc_func_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, FUNCKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // LPAR exportdesc_global_aux_ RPAR
  static boolean exportdesc_global_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "exportdesc_global_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && exportdesc_global_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // GLOBALKEY idx
  static boolean exportdesc_global_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "exportdesc_global_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, GLOBALKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // LPAR exportdesc_memory_aux_ RPAR
  static boolean exportdesc_memory_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "exportdesc_memory_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && exportdesc_memory_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // MEMORYKEY idx
  static boolean exportdesc_memory_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "exportdesc_memory_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, MEMORYKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // LPAR exportdesc_table_aux_ RPAR
  static boolean exportdesc_table_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "exportdesc_table_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && exportdesc_table_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // TABLEKEY idx
  static boolean exportdesc_table_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "exportdesc_table_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, TABLEKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // LPAR FIELDKEY IDENTIFIER? storagetype RPAR
  //             | LPAR FIELDKEY IDENTIFIER? LPAR MUTKEY storagetype RPAR RPAR
  public static boolean fieldtype(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fieldtype")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = fieldtype_0(builder_, level_ + 1);
    if (!result_) result_ = fieldtype_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, FIELDTYPE, result_);
    return result_;
  }

  // LPAR FIELDKEY IDENTIFIER? storagetype RPAR
  private static boolean fieldtype_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fieldtype_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LPAR, FIELDKEY);
    result_ = result_ && fieldtype_0_2(builder_, level_ + 1);
    result_ = result_ && storagetype(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER?
  private static boolean fieldtype_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fieldtype_0_2")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  // LPAR FIELDKEY IDENTIFIER? LPAR MUTKEY storagetype RPAR RPAR
  private static boolean fieldtype_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fieldtype_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LPAR, FIELDKEY);
    result_ = result_ && fieldtype_1_2(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, LPAR, MUTKEY);
    result_ = result_ && storagetype(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, RPAR, RPAR);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER?
  private static boolean fieldtype_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fieldtype_1_2")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  /* ********************************************************** */
  // foldeinstr_plaininstr_ | foldeinstr_block_ | foldeinstr_loop_ | foldeinstr_if_
  public static boolean foldeinstr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = foldeinstr_plaininstr_(builder_, level_ + 1);
    if (!result_) result_ = foldeinstr_block_(builder_, level_ + 1);
    if (!result_) result_ = foldeinstr_loop_(builder_, level_ + 1);
    if (!result_) result_ = foldeinstr_if_(builder_, level_ + 1);
    exit_section_(builder_, marker_, FOLDEINSTR, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPAR foldeinstr_block_aux_ RPAR IDENTIFIER?
  static boolean foldeinstr_block_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_block_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && foldeinstr_block_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, consumeToken(builder_, RPAR));
    result_ = pinned_ && foldeinstr_block__3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // IDENTIFIER?
  private static boolean foldeinstr_block__3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_block__3")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  /* ********************************************************** */
  // BLOCKKEY IDENTIFIER? blocktype? instr*
  static boolean foldeinstr_block_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_block_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, BLOCKKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, foldeinstr_block_aux__1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, foldeinstr_block_aux__2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && foldeinstr_block_aux__3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // IDENTIFIER?
  private static boolean foldeinstr_block_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_block_aux__1")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  // blocktype?
  private static boolean foldeinstr_block_aux__2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_block_aux__2")) return false;
    blocktype(builder_, level_ + 1);
    return true;
  }

  // instr*
  private static boolean foldeinstr_block_aux__3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_block_aux__3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!instr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "foldeinstr_block_aux__3", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // LPAR foldeinstr_if_aux_ RPAR
  static boolean foldeinstr_if_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_if_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && foldeinstr_if_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // IFKEY IDENTIFIER? blocktype? foldeinstr* foldeinstr_if_then_ foldeinstr_if_else_
  static boolean foldeinstr_if_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_if_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, IFKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, foldeinstr_if_aux__1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, foldeinstr_if_aux__2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, foldeinstr_if_aux__3(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, foldeinstr_if_then_(builder_, level_ + 1)) && result_;
    result_ = pinned_ && foldeinstr_if_else_(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // IDENTIFIER?
  private static boolean foldeinstr_if_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_if_aux__1")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  // blocktype?
  private static boolean foldeinstr_if_aux__2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_if_aux__2")) return false;
    blocktype(builder_, level_ + 1);
    return true;
  }

  // foldeinstr*
  private static boolean foldeinstr_if_aux__3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_if_aux__3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!foldeinstr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "foldeinstr_if_aux__3", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // LPAR foldeinstr_if_else_aux_ RPAR
  static boolean foldeinstr_if_else_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_if_else_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && foldeinstr_if_else_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // ELSEKEY instr*
  static boolean foldeinstr_if_else_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_if_else_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, ELSEKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && foldeinstr_if_else_aux__1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // instr*
  private static boolean foldeinstr_if_else_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_if_else_aux__1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!instr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "foldeinstr_if_else_aux__1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // LPAR foldeinstr_if_then_aux_ RPAR
  static boolean foldeinstr_if_then_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_if_then_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && foldeinstr_if_then_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // THENKEY instr*
  static boolean foldeinstr_if_then_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_if_then_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, THENKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && foldeinstr_if_then_aux__1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // instr*
  private static boolean foldeinstr_if_then_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_if_then_aux__1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!instr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "foldeinstr_if_then_aux__1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // LPAR foldeinstr_loop_aux_ RPAR IDENTIFIER?
  static boolean foldeinstr_loop_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_loop_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && foldeinstr_loop_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, consumeToken(builder_, RPAR));
    result_ = pinned_ && foldeinstr_loop__3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // IDENTIFIER?
  private static boolean foldeinstr_loop__3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_loop__3")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  /* ********************************************************** */
  // LOOPKEY IDENTIFIER? blocktype? instr*
  static boolean foldeinstr_loop_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_loop_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, LOOPKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, foldeinstr_loop_aux__1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, foldeinstr_loop_aux__2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && foldeinstr_loop_aux__3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // IDENTIFIER?
  private static boolean foldeinstr_loop_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_loop_aux__1")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  // blocktype?
  private static boolean foldeinstr_loop_aux__2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_loop_aux__2")) return false;
    blocktype(builder_, level_ + 1);
    return true;
  }

  // instr*
  private static boolean foldeinstr_loop_aux__3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_loop_aux__3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!instr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "foldeinstr_loop_aux__3", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // LPAR foldeinstr_plaininstr_aux_ RPAR
  static boolean foldeinstr_plaininstr_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_plaininstr_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && foldeinstr_plaininstr_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // plaininstr foldeinstr*
  static boolean foldeinstr_plaininstr_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_plaininstr_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = plaininstr(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && foldeinstr_plaininstr_aux__1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // foldeinstr*
  private static boolean foldeinstr_plaininstr_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "foldeinstr_plaininstr_aux__1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!foldeinstr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "foldeinstr_plaininstr_aux__1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // LPAR func_aux_ RPAR
  public static boolean func(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FUNC, null);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && func_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // FUNCKEY func_aux_ident_? (
  //                          inline_import typeuse?
  //                        | (inline_export (inline_export | inline_import)?)? typeuse? local* instr*
  //                       )
  static boolean func_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, FUNCKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, func_aux__1(builder_, level_ + 1));
    result_ = pinned_ && func_aux__2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // func_aux_ident_?
  private static boolean func_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_aux__1")) return false;
    func_aux_ident_(builder_, level_ + 1);
    return true;
  }

  // inline_import typeuse?
  //                        | (inline_export (inline_export | inline_import)?)? typeuse? local* instr*
  private static boolean func_aux__2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_aux__2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = func_aux__2_0(builder_, level_ + 1);
    if (!result_) result_ = func_aux__2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // inline_import typeuse?
  private static boolean func_aux__2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_aux__2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = inline_import(builder_, level_ + 1);
    result_ = result_ && func_aux__2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // typeuse?
  private static boolean func_aux__2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_aux__2_0_1")) return false;
    typeuse(builder_, level_ + 1);
    return true;
  }

  // (inline_export (inline_export | inline_import)?)? typeuse? local* instr*
  private static boolean func_aux__2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_aux__2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = func_aux__2_1_0(builder_, level_ + 1);
    result_ = result_ && func_aux__2_1_1(builder_, level_ + 1);
    result_ = result_ && func_aux__2_1_2(builder_, level_ + 1);
    result_ = result_ && func_aux__2_1_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (inline_export (inline_export | inline_import)?)?
  private static boolean func_aux__2_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_aux__2_1_0")) return false;
    func_aux__2_1_0_0(builder_, level_ + 1);
    return true;
  }

  // inline_export (inline_export | inline_import)?
  private static boolean func_aux__2_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_aux__2_1_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = inline_export(builder_, level_ + 1);
    result_ = result_ && func_aux__2_1_0_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (inline_export | inline_import)?
  private static boolean func_aux__2_1_0_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_aux__2_1_0_0_1")) return false;
    func_aux__2_1_0_0_1_0(builder_, level_ + 1);
    return true;
  }

  // inline_export | inline_import
  private static boolean func_aux__2_1_0_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_aux__2_1_0_0_1_0")) return false;
    boolean result_;
    result_ = inline_export(builder_, level_ + 1);
    if (!result_) result_ = inline_import(builder_, level_ + 1);
    return result_;
  }

  // typeuse?
  private static boolean func_aux__2_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_aux__2_1_1")) return false;
    typeuse(builder_, level_ + 1);
    return true;
  }

  // local*
  private static boolean func_aux__2_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_aux__2_1_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!local(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "func_aux__2_1_2", pos_)) break;
    }
    return true;
  }

  // instr*
  private static boolean func_aux__2_1_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_aux__2_1_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!instr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "func_aux__2_1_3", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER
  static boolean func_aux_ident_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_aux_ident_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, level_, marker_, result_, false, WebAssemblyParser::func_aux_ident_recover_);
    return result_;
  }

  /* ********************************************************** */
  // !(LPAR | RPAR | instr_key_ | <<eof>>)
  static boolean func_aux_ident_recover_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_aux_ident_recover_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !func_aux_ident_recover__0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // LPAR | RPAR | instr_key_ | <<eof>>
  private static boolean func_aux_ident_recover__0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_aux_ident_recover__0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LPAR);
    if (!result_) result_ = consumeToken(builder_, RPAR);
    if (!result_) result_ = instr_key_(builder_, level_ + 1);
    if (!result_) result_ = eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPAR FUNCKEY param* result* RPAR
  public static boolean functype(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "functype")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FUNCTYPE, null);
    result_ = consumeTokens(builder_, 2, LPAR, FUNCKEY);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, functype_2(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, functype_3(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, RPAR) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // param*
  private static boolean functype_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "functype_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!param(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "functype_2", pos_)) break;
    }
    return true;
  }

  // result*
  private static boolean functype_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "functype_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!result(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "functype_3", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // LPAR global_aux_ RPAR
  public static boolean global(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "global")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, GLOBAL, null);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && global_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // GLOBALKEY IDENTIFIER? (
  //                            inline_import globaltype
  //                          | (inline_export (inline_import | inline_export)?)? globaltype instr*
  //                         )
  static boolean global_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "global_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, GLOBALKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, global_aux__1(builder_, level_ + 1));
    result_ = pinned_ && global_aux__2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // IDENTIFIER?
  private static boolean global_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "global_aux__1")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  // inline_import globaltype
  //                          | (inline_export (inline_import | inline_export)?)? globaltype instr*
  private static boolean global_aux__2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "global_aux__2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = global_aux__2_0(builder_, level_ + 1);
    if (!result_) result_ = global_aux__2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // inline_import globaltype
  private static boolean global_aux__2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "global_aux__2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = inline_import(builder_, level_ + 1);
    result_ = result_ && globaltype(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (inline_export (inline_import | inline_export)?)? globaltype instr*
  private static boolean global_aux__2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "global_aux__2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = global_aux__2_1_0(builder_, level_ + 1);
    result_ = result_ && globaltype(builder_, level_ + 1);
    result_ = result_ && global_aux__2_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (inline_export (inline_import | inline_export)?)?
  private static boolean global_aux__2_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "global_aux__2_1_0")) return false;
    global_aux__2_1_0_0(builder_, level_ + 1);
    return true;
  }

  // inline_export (inline_import | inline_export)?
  private static boolean global_aux__2_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "global_aux__2_1_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = inline_export(builder_, level_ + 1);
    result_ = result_ && global_aux__2_1_0_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (inline_import | inline_export)?
  private static boolean global_aux__2_1_0_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "global_aux__2_1_0_0_1")) return false;
    global_aux__2_1_0_0_1_0(builder_, level_ + 1);
    return true;
  }

  // inline_import | inline_export
  private static boolean global_aux__2_1_0_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "global_aux__2_1_0_0_1_0")) return false;
    boolean result_;
    result_ = inline_import(builder_, level_ + 1);
    if (!result_) result_ = inline_export(builder_, level_ + 1);
    return result_;
  }

  // instr*
  private static boolean global_aux__2_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "global_aux__2_1_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!instr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "global_aux__2_1_2", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // GLOBALINSTR idx
  public static boolean global_instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "global_instr")) return false;
    if (!nextTokenIs(builder_, GLOBALINSTR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, GLOBAL_INSTR, null);
    result_ = consumeToken(builder_, GLOBALINSTR);
    pinned_ = result_; // pin = 1
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // valtype | globaltype_mut_
  public static boolean globaltype(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "globaltype")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, GLOBALTYPE, "<globaltype>");
    result_ = valtype(builder_, level_ + 1);
    if (!result_) result_ = globaltype_mut_(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // LPAR MUTKEY valtype RPAR
  static boolean globaltype_mut_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "globaltype_mut_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeTokens(builder_, 2, LPAR, MUTKEY);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, valtype(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, RPAR) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // FUNCKEY | EXTERNKEY | ANYKEY | EQKEY | I31KEY | NONEKEY | NOEXTERNKEY | NOFUNCKEY
  //            | STRUCTKEY | ARRAYKEY | idx
  public static boolean heaptype(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "heaptype")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, HEAPTYPE, "<heaptype>");
    result_ = consumeToken(builder_, FUNCKEY);
    if (!result_) result_ = consumeToken(builder_, EXTERNKEY);
    if (!result_) result_ = consumeToken(builder_, ANYKEY);
    if (!result_) result_ = consumeToken(builder_, EQKEY);
    if (!result_) result_ = consumeToken(builder_, I31KEY);
    if (!result_) result_ = consumeToken(builder_, NONEKEY);
    if (!result_) result_ = consumeToken(builder_, NOEXTERNKEY);
    if (!result_) result_ = consumeToken(builder_, NOFUNCKEY);
    if (!result_) result_ = consumeToken(builder_, STRUCTKEY);
    if (!result_) result_ = consumeToken(builder_, ARRAYKEY);
    if (!result_) result_ = idx(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // UNSIGNED | IDENTIFIER
  public static boolean idx(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "idx")) return false;
    if (!nextTokenIs(builder_, "<idx>", IDENTIFIER, UNSIGNED)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, IDX, "<idx>");
    result_ = consumeToken(builder_, UNSIGNED);
    if (!result_) result_ = consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // LPAR import_aux_ RPAR
  public static boolean import_$(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_$")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, IMPORT, null);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && import_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // IMPORTKEY string_fir_aux_ string_aux_ importdesc
  static boolean import_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, IMPORTKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, string_fir_aux_(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, string_aux_(builder_, level_ + 1)) && result_;
    result_ = pinned_ && importdesc(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // importdesc_func_ | importdesc_table_ | importdesc_memory_ | importdesc_global_
  public static boolean importdesc(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "importdesc")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = importdesc_func_(builder_, level_ + 1);
    if (!result_) result_ = importdesc_table_(builder_, level_ + 1);
    if (!result_) result_ = importdesc_memory_(builder_, level_ + 1);
    if (!result_) result_ = importdesc_global_(builder_, level_ + 1);
    exit_section_(builder_, marker_, IMPORTDESC, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPAR importdesc_func_aux_ RPAR
  static boolean importdesc_func_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "importdesc_func_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && importdesc_func_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // FUNCKEY IDENTIFIER? typeuse?
  static boolean importdesc_func_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "importdesc_func_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, FUNCKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, importdesc_func_aux__1(builder_, level_ + 1));
    result_ = pinned_ && importdesc_func_aux__2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // IDENTIFIER?
  private static boolean importdesc_func_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "importdesc_func_aux__1")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  // typeuse?
  private static boolean importdesc_func_aux__2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "importdesc_func_aux__2")) return false;
    typeuse(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // LPAR importdesc_global_aux_ RPAR
  static boolean importdesc_global_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "importdesc_global_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && importdesc_global_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // GLOBALKEY IDENTIFIER? globaltype
  static boolean importdesc_global_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "importdesc_global_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, GLOBALKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, importdesc_global_aux__1(builder_, level_ + 1));
    result_ = pinned_ && globaltype(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // IDENTIFIER?
  private static boolean importdesc_global_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "importdesc_global_aux__1")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  /* ********************************************************** */
  // LPAR importdesc_memory_aux_ RPAR
  static boolean importdesc_memory_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "importdesc_memory_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && importdesc_memory_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // MEMORYKEY IDENTIFIER? memtype
  static boolean importdesc_memory_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "importdesc_memory_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, MEMORYKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, importdesc_memory_aux__1(builder_, level_ + 1));
    result_ = pinned_ && memtype(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // IDENTIFIER?
  private static boolean importdesc_memory_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "importdesc_memory_aux__1")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  /* ********************************************************** */
  // LPAR importdesc_table_aux_ RPAR
  static boolean importdesc_table_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "importdesc_table_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && importdesc_table_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // TABLEKEY IDENTIFIER? tabletype
  static boolean importdesc_table_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "importdesc_table_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, TABLEKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, importdesc_table_aux__1(builder_, level_ + 1));
    result_ = pinned_ && tabletype(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // IDENTIFIER?
  private static boolean importdesc_table_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "importdesc_table_aux__1")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  /* ********************************************************** */
  // LPAR inline_data_aux_ RPAR
  public static boolean inline_data(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "inline_data")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, INLINE_DATA, null);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && inline_data_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // DATAKEY STRING*
  static boolean inline_data_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "inline_data_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, DATAKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && inline_data_aux__1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // STRING*
  private static boolean inline_data_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "inline_data_aux__1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, STRING)) break;
      if (!empty_element_parsed_guard_(builder_, "inline_data_aux__1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // REFTYPE LPAR inline_elem_aux_ RPAR
  public static boolean inline_elem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "inline_elem")) return false;
    if (!nextTokenIs(builder_, REFTYPE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, INLINE_ELEM, null);
    result_ = consumeTokens(builder_, 0, REFTYPE, LPAR);
    result_ = result_ && inline_elem_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 3
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // ELEMKEY (instr+ | elemlist)?
  static boolean inline_elem_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "inline_elem_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, ELEMKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && inline_elem_aux__1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // (instr+ | elemlist)?
  private static boolean inline_elem_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "inline_elem_aux__1")) return false;
    inline_elem_aux__1_0(builder_, level_ + 1);
    return true;
  }

  // instr+ | elemlist
  private static boolean inline_elem_aux__1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "inline_elem_aux__1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = inline_elem_aux__1_0_0(builder_, level_ + 1);
    if (!result_) result_ = elemlist(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // instr+
  private static boolean inline_elem_aux__1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "inline_elem_aux__1_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = instr(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!instr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "inline_elem_aux__1_0_0", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPAR EXPORTKEY STRING RPAR
  public static boolean inline_export(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "inline_export")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, INLINE_EXPORT, null);
    result_ = consumeTokens(builder_, 2, LPAR, EXPORTKEY, STRING, RPAR);
    pinned_ = result_; // pin = 2
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // LPAR IMPORTKEY STRING STRING RPAR
  public static boolean inline_import(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "inline_import")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LPAR, IMPORTKEY, STRING, STRING, RPAR);
    exit_section_(builder_, marker_, INLINE_IMPORT, result_);
    return result_;
  }

  /* ********************************************************** */
  // foldeinstr | plaininstr | blockinstr
  public static boolean instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "instr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, INSTR, "<instr>");
    result_ = foldeinstr(builder_, level_ + 1);
    if (!result_) result_ = plaininstr(builder_, level_ + 1);
    if (!result_) result_ = blockinstr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // BLOCKKEY | LOOPKEY | IFKEY
  //                      | CONTROLINSTR | CONTROLINSTR_IDX | CALLINSTR | BRTABLEINSTR | CALLINDIRECTINSTR
  //                      | REFISNULLINST | REFNULLINSTR | REFFUNCINSTR
  //                      | REFTEST | REFCAST | REFEQ | REFI31 | I31GET | REFASNONNULL | EXTERNCONVERT
  //                      | PARAMETRICINSTR
  //                      | LOCALINSTR | GLOBALINSTR
  //                      | STRUCTNEW | STRUCTGET | STRUCTSET
  //                      | ARRAYNEW | ARRAYGET | ARRAYSET | ARRAYLEN | ARRAYCOPY | ARRAYFILL | ARRAYINITDATA | ARRAYINITELEM
  //                      | TABLEINSTR_IDX | TABLECOPYINSTR | TABLEINITINSTR | ELEMDROPINSTR
  //                      | MEMORYINSTR | MEMORYINSTR_IDX | MEMORYINSTR_MEMARG
  //                      | ICONST | FCONST | NUMERICINSTR
  static boolean instr_key_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "instr_key_")) return false;
    boolean result_;
    result_ = consumeToken(builder_, BLOCKKEY);
    if (!result_) result_ = consumeToken(builder_, LOOPKEY);
    if (!result_) result_ = consumeToken(builder_, IFKEY);
    if (!result_) result_ = consumeToken(builder_, CONTROLINSTR);
    if (!result_) result_ = consumeToken(builder_, CONTROLINSTR_IDX);
    if (!result_) result_ = consumeToken(builder_, CALLINSTR);
    if (!result_) result_ = consumeToken(builder_, BRTABLEINSTR);
    if (!result_) result_ = consumeToken(builder_, CALLINDIRECTINSTR);
    if (!result_) result_ = consumeToken(builder_, REFISNULLINST);
    if (!result_) result_ = consumeToken(builder_, REFNULLINSTR);
    if (!result_) result_ = consumeToken(builder_, REFFUNCINSTR);
    if (!result_) result_ = consumeToken(builder_, REFTEST);
    if (!result_) result_ = consumeToken(builder_, REFCAST);
    if (!result_) result_ = consumeToken(builder_, REFEQ);
    if (!result_) result_ = consumeToken(builder_, REFI31);
    if (!result_) result_ = consumeToken(builder_, I31GET);
    if (!result_) result_ = consumeToken(builder_, REFASNONNULL);
    if (!result_) result_ = consumeToken(builder_, EXTERNCONVERT);
    if (!result_) result_ = consumeToken(builder_, PARAMETRICINSTR);
    if (!result_) result_ = consumeToken(builder_, LOCALINSTR);
    if (!result_) result_ = consumeToken(builder_, GLOBALINSTR);
    if (!result_) result_ = consumeToken(builder_, STRUCTNEW);
    if (!result_) result_ = consumeToken(builder_, STRUCTGET);
    if (!result_) result_ = consumeToken(builder_, STRUCTSET);
    if (!result_) result_ = consumeToken(builder_, ARRAYNEW);
    if (!result_) result_ = consumeToken(builder_, ARRAYGET);
    if (!result_) result_ = consumeToken(builder_, ARRAYSET);
    if (!result_) result_ = consumeToken(builder_, ARRAYLEN);
    if (!result_) result_ = consumeToken(builder_, ARRAYCOPY);
    if (!result_) result_ = consumeToken(builder_, ARRAYFILL);
    if (!result_) result_ = consumeToken(builder_, ARRAYINITDATA);
    if (!result_) result_ = consumeToken(builder_, ARRAYINITELEM);
    if (!result_) result_ = consumeToken(builder_, TABLEINSTR_IDX);
    if (!result_) result_ = consumeToken(builder_, TABLECOPYINSTR);
    if (!result_) result_ = consumeToken(builder_, TABLEINITINSTR);
    if (!result_) result_ = consumeToken(builder_, ELEMDROPINSTR);
    if (!result_) result_ = consumeToken(builder_, MEMORYINSTR);
    if (!result_) result_ = consumeToken(builder_, MEMORYINSTR_IDX);
    if (!result_) result_ = consumeToken(builder_, MEMORYINSTR_MEMARG);
    if (!result_) result_ = consumeToken(builder_, ICONST);
    if (!result_) result_ = consumeToken(builder_, FCONST);
    if (!result_) result_ = consumeToken(builder_, NUMERICINSTR);
    return result_;
  }

  /* ********************************************************** */
  // module | modulefield
  static boolean item_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_;
    result_ = module(builder_, level_ + 1);
    if (!result_) result_ = modulefield(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // !(RPAR | <<eof>>)
  static boolean item_recover_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_recover_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !item_recover__0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // RPAR | <<eof>>
  private static boolean item_recover__0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_recover__0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, RPAR);
    if (!result_) result_ = eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // BAD_TOKEN
  public static boolean lexer_tokens(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lexer_tokens")) return false;
    if (!nextTokenIs(builder_, BAD_TOKEN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BAD_TOKEN);
    exit_section_(builder_, marker_, LEXER_TOKENS, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPAR local_aux_ RPAR
  public static boolean local(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "local")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LOCAL, null);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && local_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // LOCALKEY (IDENTIFIER valtype | valtype*)
  static boolean local_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "local_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, LOCALKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && local_aux__1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // IDENTIFIER valtype | valtype*
  private static boolean local_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "local_aux__1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = local_aux__1_0(builder_, level_ + 1);
    if (!result_) result_ = local_aux__1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER valtype
  private static boolean local_aux__1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "local_aux__1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && valtype(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // valtype*
  private static boolean local_aux__1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "local_aux__1_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!valtype(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "local_aux__1_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // LOCALINSTR idx
  public static boolean local_instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "local_instr")) return false;
    if (!nextTokenIs(builder_, LOCALINSTR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LOCAL_INSTR, null);
    result_ = consumeToken(builder_, LOCALINSTR);
    pinned_ = result_; // pin = 1
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // LPAR memaux_ RPAR
  public static boolean mem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mem")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MEM, null);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && memaux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // MEMORYKEY IDENTIFIER? (
  //                        inline_data
  //                      | inline_import? memtype
  //                      | inline_export (inline_import | inline_export | inline_data)* memtype
  //                     )
  static boolean memaux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "memaux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, MEMORYKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, memaux__1(builder_, level_ + 1));
    result_ = pinned_ && memaux__2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // IDENTIFIER?
  private static boolean memaux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "memaux__1")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  // inline_data
  //                      | inline_import? memtype
  //                      | inline_export (inline_import | inline_export | inline_data)* memtype
  private static boolean memaux__2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "memaux__2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = inline_data(builder_, level_ + 1);
    if (!result_) result_ = memaux__2_1(builder_, level_ + 1);
    if (!result_) result_ = memaux__2_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // inline_import? memtype
  private static boolean memaux__2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "memaux__2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = memaux__2_1_0(builder_, level_ + 1);
    result_ = result_ && memtype(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // inline_import?
  private static boolean memaux__2_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "memaux__2_1_0")) return false;
    inline_import(builder_, level_ + 1);
    return true;
  }

  // inline_export (inline_import | inline_export | inline_data)* memtype
  private static boolean memaux__2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "memaux__2_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = inline_export(builder_, level_ + 1);
    result_ = result_ && memaux__2_2_1(builder_, level_ + 1);
    result_ = result_ && memtype(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (inline_import | inline_export | inline_data)*
  private static boolean memaux__2_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "memaux__2_2_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!memaux__2_2_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "memaux__2_2_1", pos_)) break;
    }
    return true;
  }

  // inline_import | inline_export | inline_data
  private static boolean memaux__2_2_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "memaux__2_2_1_0")) return false;
    boolean result_;
    result_ = inline_import(builder_, level_ + 1);
    if (!result_) result_ = inline_export(builder_, level_ + 1);
    if (!result_) result_ = inline_data(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // MEMORYINSTR_IDX idx
  public static boolean memory_idx_instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "memory_idx_instr")) return false;
    if (!nextTokenIs(builder_, MEMORYINSTR_IDX)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MEMORY_IDX_INSTR, null);
    result_ = consumeToken(builder_, MEMORYINSTR_IDX);
    pinned_ = result_; // pin = 1
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // UNSIGNED UNSIGNED?
  public static boolean memtype(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "memtype")) return false;
    if (!nextTokenIs(builder_, UNSIGNED)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, UNSIGNED);
    result_ = result_ && memtype_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, MEMTYPE, result_);
    return result_;
  }

  // UNSIGNED?
  private static boolean memtype_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "memtype_1")) return false;
    consumeToken(builder_, UNSIGNED);
    return true;
  }

  /* ********************************************************** */
  // LPAR MEMORYKEY idx RPAR
  static boolean memuse_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "memuse_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeTokens(builder_, 2, LPAR, MEMORYKEY);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, idx(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, RPAR) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // LPAR module_aux_ RPAR
  public static boolean module(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MODULE, null);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && module_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // MODULEKEY IDENTIFIER? modulefield*
  static boolean module_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, MODULEKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, module_aux__1(builder_, level_ + 1));
    result_ = pinned_ && module_aux__2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // IDENTIFIER?
  private static boolean module_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_aux__1")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  // modulefield*
  private static boolean module_aux__2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "module_aux__2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!modulefield(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "module_aux__2", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // type | import | func | table | mem | global | export | start | elem | data
  public static boolean modulefield(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "modulefield")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = type(builder_, level_ + 1);
    if (!result_) result_ = import_$(builder_, level_ + 1);
    if (!result_) result_ = func(builder_, level_ + 1);
    if (!result_) result_ = table(builder_, level_ + 1);
    if (!result_) result_ = mem(builder_, level_ + 1);
    if (!result_) result_ = global(builder_, level_ + 1);
    if (!result_) result_ = export(builder_, level_ + 1);
    if (!result_) result_ = start(builder_, level_ + 1);
    if (!result_) result_ = elem(builder_, level_ + 1);
    if (!result_) result_ = data(builder_, level_ + 1);
    exit_section_(builder_, marker_, MODULEFIELD, result_);
    return result_;
  }

  /* ********************************************************** */
  // OFFSETEQKEY UNSIGNED
  public static boolean offseteq(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "offseteq")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, OFFSETEQ, "<offseteq>");
    result_ = consumeTokens(builder_, 1, OFFSETEQKEY, UNSIGNED);
    pinned_ = result_; // pin = 1
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::offseteq_recover_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // !(aligneq | instr | RPAR | <<eof>>)
  static boolean offseteq_recover_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "offseteq_recover_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !offseteq_recover__0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // aligneq | instr | RPAR | <<eof>>
  private static boolean offseteq_recover__0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "offseteq_recover__0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = aligneq(builder_, level_ + 1);
    if (!result_) result_ = instr(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, RPAR);
    if (!result_) result_ = eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPAR param_aux_ RPAR
  public static boolean param(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "param")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PARAM, null);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && param_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // PARAMKEY (IDENTIFIER valtype | valtype*)
  static boolean param_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "param_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, PARAMKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && param_aux__1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // IDENTIFIER valtype | valtype*
  private static boolean param_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "param_aux__1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = param_aux__1_0(builder_, level_ + 1);
    if (!result_) result_ = param_aux__1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER valtype
  private static boolean param_aux__1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "param_aux__1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && valtype(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // valtype*
  private static boolean param_aux__1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "param_aux__1_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!valtype(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "param_aux__1_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // CONTROLINSTR | CONTROLINSTR_IDX idx | call_instr | BRTABLEINSTR idx+ | call_indirect_instr
  //              // reference
  //              | REFISNULLINST | REFNULLINSTR heaptype | ref_func_instr
  //              | REFTEST heaptype | REFCAST heaptype | REFEQ | REFI31 | I31GET | REFASNONNULL | EXTERNCONVERT
  //              // parametric
  //              | PARAMETRICINSTR
  //              // variable
  //              | local_instr | global_instr
  //              // GC struct
  //              | struct_new_instr | struct_get_instr | struct_set_instr
  //              // GC array
  //              | array_new_instr | array_get_instr | array_set_instr | array_len_instr
  //              | ARRAYCOPY idx idx | ARRAYFILL idx
  //              | ARRAYINITDATA idx idx | ARRAYINITELEM idx idx
  //              // table
  //              | table_idx_instr | table_copy_instr | table_init_instr | elem_drop_instr
  //              // memory
  //              | MEMORYINSTR | memory_idx_instr | MEMORYINSTR_MEMARG offseteq? aligneq?
  //              // numeric
  //              | ICONST (UNSIGNED | SIGNED) | FCONST (FLOAT | UNSIGNED | SIGNED) | NUMERICINSTR
  public static boolean plaininstr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PLAININSTR, "<plaininstr>");
    result_ = consumeToken(builder_, CONTROLINSTR);
    if (!result_) result_ = plaininstr_1(builder_, level_ + 1);
    if (!result_) result_ = call_instr(builder_, level_ + 1);
    if (!result_) result_ = plaininstr_3(builder_, level_ + 1);
    if (!result_) result_ = call_indirect_instr(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, REFISNULLINST);
    if (!result_) result_ = plaininstr_6(builder_, level_ + 1);
    if (!result_) result_ = ref_func_instr(builder_, level_ + 1);
    if (!result_) result_ = plaininstr_8(builder_, level_ + 1);
    if (!result_) result_ = plaininstr_9(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, REFEQ);
    if (!result_) result_ = consumeToken(builder_, REFI31);
    if (!result_) result_ = consumeToken(builder_, I31GET);
    if (!result_) result_ = consumeToken(builder_, REFASNONNULL);
    if (!result_) result_ = consumeToken(builder_, EXTERNCONVERT);
    if (!result_) result_ = consumeToken(builder_, PARAMETRICINSTR);
    if (!result_) result_ = local_instr(builder_, level_ + 1);
    if (!result_) result_ = global_instr(builder_, level_ + 1);
    if (!result_) result_ = struct_new_instr(builder_, level_ + 1);
    if (!result_) result_ = struct_get_instr(builder_, level_ + 1);
    if (!result_) result_ = struct_set_instr(builder_, level_ + 1);
    if (!result_) result_ = array_new_instr(builder_, level_ + 1);
    if (!result_) result_ = array_get_instr(builder_, level_ + 1);
    if (!result_) result_ = array_set_instr(builder_, level_ + 1);
    if (!result_) result_ = array_len_instr(builder_, level_ + 1);
    if (!result_) result_ = plaininstr_25(builder_, level_ + 1);
    if (!result_) result_ = plaininstr_26(builder_, level_ + 1);
    if (!result_) result_ = plaininstr_27(builder_, level_ + 1);
    if (!result_) result_ = plaininstr_28(builder_, level_ + 1);
    if (!result_) result_ = table_idx_instr(builder_, level_ + 1);
    if (!result_) result_ = table_copy_instr(builder_, level_ + 1);
    if (!result_) result_ = table_init_instr(builder_, level_ + 1);
    if (!result_) result_ = elem_drop_instr(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, MEMORYINSTR);
    if (!result_) result_ = memory_idx_instr(builder_, level_ + 1);
    if (!result_) result_ = plaininstr_35(builder_, level_ + 1);
    if (!result_) result_ = plaininstr_36(builder_, level_ + 1);
    if (!result_) result_ = plaininstr_37(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, NUMERICINSTR);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // CONTROLINSTR_IDX idx
  private static boolean plaininstr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CONTROLINSTR_IDX);
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // BRTABLEINSTR idx+
  private static boolean plaininstr_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BRTABLEINSTR);
    result_ = result_ && plaininstr_3_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // idx+
  private static boolean plaininstr_3_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr_3_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = idx(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!idx(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "plaininstr_3_1", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // REFNULLINSTR heaptype
  private static boolean plaininstr_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr_6")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, REFNULLINSTR);
    result_ = result_ && heaptype(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // REFTEST heaptype
  private static boolean plaininstr_8(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr_8")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, REFTEST);
    result_ = result_ && heaptype(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // REFCAST heaptype
  private static boolean plaininstr_9(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr_9")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, REFCAST);
    result_ = result_ && heaptype(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ARRAYCOPY idx idx
  private static boolean plaininstr_25(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr_25")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ARRAYCOPY);
    result_ = result_ && idx(builder_, level_ + 1);
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ARRAYFILL idx
  private static boolean plaininstr_26(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr_26")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ARRAYFILL);
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ARRAYINITDATA idx idx
  private static boolean plaininstr_27(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr_27")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ARRAYINITDATA);
    result_ = result_ && idx(builder_, level_ + 1);
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ARRAYINITELEM idx idx
  private static boolean plaininstr_28(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr_28")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ARRAYINITELEM);
    result_ = result_ && idx(builder_, level_ + 1);
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // MEMORYINSTR_MEMARG offseteq? aligneq?
  private static boolean plaininstr_35(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr_35")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, MEMORYINSTR_MEMARG);
    result_ = result_ && plaininstr_35_1(builder_, level_ + 1);
    result_ = result_ && plaininstr_35_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // offseteq?
  private static boolean plaininstr_35_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr_35_1")) return false;
    offseteq(builder_, level_ + 1);
    return true;
  }

  // aligneq?
  private static boolean plaininstr_35_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr_35_2")) return false;
    aligneq(builder_, level_ + 1);
    return true;
  }

  // ICONST (UNSIGNED | SIGNED)
  private static boolean plaininstr_36(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr_36")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ICONST);
    result_ = result_ && plaininstr_36_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // UNSIGNED | SIGNED
  private static boolean plaininstr_36_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr_36_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, UNSIGNED);
    if (!result_) result_ = consumeToken(builder_, SIGNED);
    return result_;
  }

  // FCONST (FLOAT | UNSIGNED | SIGNED)
  private static boolean plaininstr_37(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr_37")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, FCONST);
    result_ = result_ && plaininstr_37_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // FLOAT | UNSIGNED | SIGNED
  private static boolean plaininstr_37_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "plaininstr_37_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, FLOAT);
    if (!result_) result_ = consumeToken(builder_, UNSIGNED);
    if (!result_) result_ = consumeToken(builder_, SIGNED);
    return result_;
  }

  /* ********************************************************** */
  // REFFUNCINSTR idx
  public static boolean ref_func_instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ref_func_instr")) return false;
    if (!nextTokenIs(builder_, REFFUNCINSTR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, REF_FUNC_INSTR, null);
    result_ = consumeToken(builder_, REFFUNCINSTR);
    pinned_ = result_; // pin = 1
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // LPAR REFKEY NULLKEY? idx RPAR
  public static boolean reftype_ref_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "reftype_ref_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, REFTYPE_REF_, null);
    result_ = consumeTokens(builder_, 2, LPAR, REFKEY);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, reftype_ref__2(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, idx(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, RPAR) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // NULLKEY?
  private static boolean reftype_ref__2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "reftype_ref__2")) return false;
    consumeToken(builder_, NULLKEY);
    return true;
  }

  /* ********************************************************** */
  // LPAR result_aux_ RPAR
  public static boolean result(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "result")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, RESULT, null);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && result_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // RESULTKEY valtype*
  static boolean result_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "result_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, RESULTKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && result_aux__1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // valtype*
  private static boolean result_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "result_aux__1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!valtype(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "result_aux__1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // LPAR start_aux_ RPAR
  public static boolean start(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "start")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, START, null);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && start_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // STARTKEY idx
  static boolean start_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "start_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, STARTKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // valtype | PACKEDTYPE
  public static boolean storagetype(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "storagetype")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STORAGETYPE, "<storagetype>");
    result_ = valtype(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, PACKEDTYPE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // STRING
  static boolean string_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string_aux_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, STRING);
    exit_section_(builder_, level_, marker_, result_, false, WebAssemblyParser::string_recover_);
    return result_;
  }

  /* ********************************************************** */
  // STRING
  static boolean string_fir_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string_fir_aux_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, STRING);
    exit_section_(builder_, level_, marker_, result_, false, WebAssemblyParser::string_fir_recover_);
    return result_;
  }

  /* ********************************************************** */
  // !(STRING | LPAR | RPAR | <<eof>>)
  static boolean string_fir_recover_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string_fir_recover_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !string_fir_recover__0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // STRING | LPAR | RPAR | <<eof>>
  private static boolean string_fir_recover__0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string_fir_recover__0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STRING);
    if (!result_) result_ = consumeToken(builder_, LPAR);
    if (!result_) result_ = consumeToken(builder_, RPAR);
    if (!result_) result_ = eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // !(LPAR | RPAR | <<eof>>)
  static boolean string_recover_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string_recover_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !string_recover__0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // LPAR | RPAR | <<eof>>
  private static boolean string_recover__0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string_recover__0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LPAR);
    if (!result_) result_ = consumeToken(builder_, RPAR);
    if (!result_) result_ = eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // STRUCTGET idx idx
  public static boolean struct_get_instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_get_instr")) return false;
    if (!nextTokenIs(builder_, STRUCTGET)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRUCT_GET_INSTR, null);
    result_ = consumeToken(builder_, STRUCTGET);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, idx(builder_, level_ + 1));
    result_ = pinned_ && idx(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // STRUCTNEW idx
  public static boolean struct_new_instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_new_instr")) return false;
    if (!nextTokenIs(builder_, STRUCTNEW)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRUCT_NEW_INSTR, null);
    result_ = consumeToken(builder_, STRUCTNEW);
    pinned_ = result_; // pin = 1
    result_ = result_ && idx(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // STRUCTSET idx idx
  public static boolean struct_set_instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_set_instr")) return false;
    if (!nextTokenIs(builder_, STRUCTSET)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRUCT_SET_INSTR, null);
    result_ = consumeToken(builder_, STRUCTSET);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, idx(builder_, level_ + 1));
    result_ = pinned_ && idx(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // LPAR STRUCTKEY fieldtype* RPAR
  public static boolean structtype(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "structtype")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRUCTTYPE, null);
    result_ = consumeTokens(builder_, 2, LPAR, STRUCTKEY);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, structtype_2(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, RPAR) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // fieldtype*
  private static boolean structtype_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "structtype_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!fieldtype(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "structtype_2", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // LPAR subtype_kind_ typeuse_inline_? RPAR
  static boolean subtype_decl_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "subtype_decl_")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && subtype_kind_(builder_, level_ + 1);
    result_ = result_ && subtype_decl__2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // typeuse_inline_?
  private static boolean subtype_decl__2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "subtype_decl__2")) return false;
    consumeToken(builder_, TYPEUSE_INLINE_);
    return true;
  }

  /* ********************************************************** */
  // SUBKEY FINALKEY? | FINALKEY
  static boolean subtype_kind_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "subtype_kind_")) return false;
    if (!nextTokenIs(builder_, "", FINALKEY, SUBKEY)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = subtype_kind__0(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, FINALKEY);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // SUBKEY FINALKEY?
  private static boolean subtype_kind__0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "subtype_kind__0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SUBKEY);
    result_ = result_ && subtype_kind__0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // FINALKEY?
  private static boolean subtype_kind__0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "subtype_kind__0_1")) return false;
    consumeToken(builder_, FINALKEY);
    return true;
  }

  /* ********************************************************** */
  // LPAR table_aux_ RPAR
  public static boolean table(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TABLE, null);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && table_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // TABLEKEY table_aux_ident_? (
  //                           inline_elem
  //                         | inline_import? tabletype
  //                         | inline_export (inline_import | inline_export | inline_elem)?
  //                        )
  static boolean table_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, TABLEKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, table_aux__1(builder_, level_ + 1));
    result_ = pinned_ && table_aux__2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // table_aux_ident_?
  private static boolean table_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_aux__1")) return false;
    table_aux_ident_(builder_, level_ + 1);
    return true;
  }

  // inline_elem
  //                         | inline_import? tabletype
  //                         | inline_export (inline_import | inline_export | inline_elem)?
  private static boolean table_aux__2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_aux__2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = inline_elem(builder_, level_ + 1);
    if (!result_) result_ = table_aux__2_1(builder_, level_ + 1);
    if (!result_) result_ = table_aux__2_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // inline_import? tabletype
  private static boolean table_aux__2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_aux__2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = table_aux__2_1_0(builder_, level_ + 1);
    result_ = result_ && tabletype(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // inline_import?
  private static boolean table_aux__2_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_aux__2_1_0")) return false;
    inline_import(builder_, level_ + 1);
    return true;
  }

  // inline_export (inline_import | inline_export | inline_elem)?
  private static boolean table_aux__2_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_aux__2_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = inline_export(builder_, level_ + 1);
    result_ = result_ && table_aux__2_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (inline_import | inline_export | inline_elem)?
  private static boolean table_aux__2_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_aux__2_2_1")) return false;
    table_aux__2_2_1_0(builder_, level_ + 1);
    return true;
  }

  // inline_import | inline_export | inline_elem
  private static boolean table_aux__2_2_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_aux__2_2_1_0")) return false;
    boolean result_;
    result_ = inline_import(builder_, level_ + 1);
    if (!result_) result_ = inline_export(builder_, level_ + 1);
    if (!result_) result_ = inline_elem(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER
  static boolean table_aux_ident_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_aux_ident_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, level_, marker_, result_, false, WebAssemblyParser::table_aux_ident_recover_);
    return result_;
  }

  /* ********************************************************** */
  // !(LPAR | RPAR | REFTYPE | memtype | <<eof>>)
  static boolean table_aux_ident_recover_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_aux_ident_recover_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !table_aux_ident_recover__0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // LPAR | RPAR | REFTYPE | memtype | <<eof>>
  private static boolean table_aux_ident_recover__0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_aux_ident_recover__0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LPAR);
    if (!result_) result_ = consumeToken(builder_, RPAR);
    if (!result_) result_ = consumeToken(builder_, REFTYPE);
    if (!result_) result_ = memtype(builder_, level_ + 1);
    if (!result_) result_ = eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // TABLECOPYINSTR idx? idx?
  public static boolean table_copy_instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_copy_instr")) return false;
    if (!nextTokenIs(builder_, TABLECOPYINSTR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TABLE_COPY_INSTR, null);
    result_ = consumeToken(builder_, TABLECOPYINSTR);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, table_copy_instr_1(builder_, level_ + 1));
    result_ = pinned_ && table_copy_instr_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // idx?
  private static boolean table_copy_instr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_copy_instr_1")) return false;
    idx(builder_, level_ + 1);
    return true;
  }

  // idx?
  private static boolean table_copy_instr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_copy_instr_2")) return false;
    idx(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // TABLEINSTR_IDX idx?
  public static boolean table_idx_instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_idx_instr")) return false;
    if (!nextTokenIs(builder_, TABLEINSTR_IDX)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TABLE_IDX_INSTR, null);
    result_ = consumeToken(builder_, TABLEINSTR_IDX);
    pinned_ = result_; // pin = 1
    result_ = result_ && table_idx_instr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // idx?
  private static boolean table_idx_instr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_idx_instr_1")) return false;
    idx(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // TABLEINITINSTR idx idx?
  public static boolean table_init_instr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_init_instr")) return false;
    if (!nextTokenIs(builder_, TABLEINITINSTR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TABLE_INIT_INSTR, null);
    result_ = consumeToken(builder_, TABLEINITINSTR);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, idx(builder_, level_ + 1));
    result_ = pinned_ && table_init_instr_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // idx?
  private static boolean table_init_instr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "table_init_instr_2")) return false;
    idx(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // memtype REFTYPE
  public static boolean tabletype(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tabletype")) return false;
    if (!nextTokenIs(builder_, UNSIGNED)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = memtype(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, REFTYPE);
    exit_section_(builder_, marker_, TABLETYPE, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPAR type_aux_ RPAR
  public static boolean type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TYPE, null);
    result_ = consumeToken(builder_, LPAR);
    result_ = result_ && type_aux_(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, RPAR);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // TYPEKEY IDENTIFIER? subtype_decl_? (functype | structtype | arraytype)
  static boolean type_aux_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_aux_")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, TYPEKEY);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, type_aux__1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, type_aux__2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && type_aux__3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, WebAssemblyParser::item_recover_);
    return result_ || pinned_;
  }

  // IDENTIFIER?
  private static boolean type_aux__1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_aux__1")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  // subtype_decl_?
  private static boolean type_aux__2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_aux__2")) return false;
    subtype_decl_(builder_, level_ + 1);
    return true;
  }

  // functype | structtype | arraytype
  private static boolean type_aux__3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_aux__3")) return false;
    boolean result_;
    result_ = functype(builder_, level_ + 1);
    if (!result_) result_ = structtype(builder_, level_ + 1);
    if (!result_) result_ = arraytype(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // typeuse_typeref param+ result+
  //           | typeuse_typeref param+
  //           | typeuse_typeref        result+
  //           | typeuse_typeref
  //           |                 param+ result+
  //           |                 param+
  //           |                        result+
  public static boolean typeuse(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeuse")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = typeuse_0(builder_, level_ + 1);
    if (!result_) result_ = typeuse_1(builder_, level_ + 1);
    if (!result_) result_ = typeuse_2(builder_, level_ + 1);
    if (!result_) result_ = typeuse_typeref(builder_, level_ + 1);
    if (!result_) result_ = typeuse_4(builder_, level_ + 1);
    if (!result_) result_ = typeuse_5(builder_, level_ + 1);
    if (!result_) result_ = typeuse_6(builder_, level_ + 1);
    exit_section_(builder_, marker_, TYPEUSE, result_);
    return result_;
  }

  // typeuse_typeref param+ result+
  private static boolean typeuse_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeuse_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = typeuse_typeref(builder_, level_ + 1);
    result_ = result_ && typeuse_0_1(builder_, level_ + 1);
    result_ = result_ && typeuse_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // param+
  private static boolean typeuse_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeuse_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = param(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!param(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "typeuse_0_1", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // result+
  private static boolean typeuse_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeuse_0_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = result(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!result(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "typeuse_0_2", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // typeuse_typeref param+
  private static boolean typeuse_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeuse_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = typeuse_typeref(builder_, level_ + 1);
    result_ = result_ && typeuse_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // param+
  private static boolean typeuse_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeuse_1_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = param(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!param(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "typeuse_1_1", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // typeuse_typeref        result+
  private static boolean typeuse_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeuse_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = typeuse_typeref(builder_, level_ + 1);
    result_ = result_ && typeuse_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // result+
  private static boolean typeuse_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeuse_2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = result(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!result(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "typeuse_2_1", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // param+ result+
  private static boolean typeuse_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeuse_4")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = typeuse_4_0(builder_, level_ + 1);
    result_ = result_ && typeuse_4_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // param+
  private static boolean typeuse_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeuse_4_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = param(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!param(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "typeuse_4_0", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // result+
  private static boolean typeuse_4_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeuse_4_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = result(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!result(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "typeuse_4_1", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // param+
  private static boolean typeuse_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeuse_5")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = param(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!param(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "typeuse_5", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // result+
  private static boolean typeuse_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeuse_6")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = result(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!result(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "typeuse_6", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPAR TYPEKEY idx RPAR
  public static boolean typeuse_typeref(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "typeuse_typeref")) return false;
    if (!nextTokenIs(builder_, LPAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TYPEUSE_TYPEREF, null);
    result_ = consumeTokens(builder_, 2, LPAR, TYPEKEY);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, idx(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, RPAR) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // NUMTYPE | REFTYPE | reftype_ref_
  public static boolean valtype(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "valtype")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, VALTYPE, "<valtype>");
    result_ = consumeToken(builder_, NUMTYPE);
    if (!result_) result_ = consumeToken(builder_, REFTYPE);
    if (!result_) result_ = reftype_ref_(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // item_*
  static boolean webAssemblyFile(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "webAssemblyFile")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!item_(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "webAssemblyFile", pos_)) break;
    }
    return true;
  }

}
