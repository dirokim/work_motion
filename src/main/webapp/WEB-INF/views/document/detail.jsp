<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<meta name="viewport"
	content="initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0" />
<style>
.file-list {
	height: 200px;
	overflow: auto;
	border: 1px solid #989898;
	padding: 10px;
}

.file-list .filebox p {
	font-size: 14px;
	margin-top: 10px;
	display: inline-block;
}

.file-list .filebox .delete i {
	color: #ff5353;
	margin-left: 5px;
}

body, img, p {
	margin: 0;
	padding: 0
}

span.editor_span {
	display: block;
	width: auto !important;
	margin: 5px
}

span.editor_span table {
	word-break: normal
}

span.editor_span, span.editor_span table, span.editor_span p, span.editor_span span
	{
	font-size: 12px;
	font-family: dotum;
	line-height: 1.5
}

.editor_span td {
	padding: 0 !important
}

.editor_span ol, .editor_span ul {
	list-style-position: inside;
	padding-left: 40px
}

img {
	border: 0
}

/* sign_type1 */
div.sign_type1 {
	display: inline-block;
	margin-right: -1px;
	font-size: 12px
}

div.sign_type1.last table.tb_sign_type1 {
	border-left: 0
}

div.sign_type1 table.tb_sign_type1, table.sign_member {
	border-spacing: 0;
	border-collapse: collapse;
	width: 60px
}

div.sign_type1 table.tb_sign_type1 {
	border: 1px solid #666
}

div.sign_type1 table.tb_sign_type1 tbody tr th {
	width: 20px;
	background: #eee;
	padding: 4px;
	text-align: center;
	word-break: break-word;
	white-space: normal
}

div.sign_type1 table.tb_sign_type1 tbody tr td {
	border-left: 1px solid #666;
	padding: 0 !important
}

div.sign_type1 table.tb_sign_type1 tbody tr td table.sign_member tbody tr td
	{
	height: 26px;
	border-left: 0;
	border-bottom: 1px solid #666;
	min-height: 26px;
	vertical-align: middle
}

div.sign_type1 table.tb_sign_type1 tbody tr td table.sign_member tbody tr td.last
	{
	border-bottom: 0
}

div.sign_type1 table.tb_sign_type1 tbody tr td table.sign_member tbody tr td span
	{
	display: block;
	text-align: center;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	width: 70px;
	padding: 4px;
	font-size: 12px
}

div.sign_type1 table.tb_sign_type1 tbody tr td table.sign_member tbody tr td span.sign_name
	{
	height: 36px
}

div.sign_type1 table.tb_sign_type1 tbody tr td table.sign_member tbody tr td span.arbitrary
	{
	background: url(../images/arbitrary.gif) no-repeat
}

div.sign_type1 table.tb_sign_type1 tbody tr td table.sign_member tbody tr td span img
	{
	width: auto;
	height: 100%
}

div.sign_type1 table.tb_sign_type1 tbody tr td table.sign_member tbody tr td span.doc_return
	{
	padding: 0;
}

/* sign_type_new */
div.sign_type_new {
	display: inline-block;
	margin-right: -1px;
	font-size: 12px
}

div.sign_type_new.last table.tb_sign_type1 {
	border-left: 0
}

div.sign_type_new table.tb_sign_type1 {
	border: 1px solid #666
}

div.sign_type_new table.tb_sign_type1 tbody tr td table.sign_member tbody tr td span.sign_name
	{
	height: auto
}

div.sign_type_new table.tb_sign_type1 tbody tr th {
	width: 15px;
	background: #eee;
	padding: 6px;
	text-align: center;
	word-break: break-word;
	white-space: normal
}

div.sign_type_new table.tb_sign_type1 tbody tr td {
	border-left: 1px solid #666;
	padding: 0 !important
}

div.sign_type_new table.tb_sign_type1 tbody tr td table.sign_member tbody tr td
	{
	height: 26px;
	border-left: 0;
	border-bottom: 1px solid #666;
	min-height: 26px
}

div.sign_type_new table.tb_sign_type1 tbody tr td table.sign_member tbody tr td.last
	{
	border-bottom: 0
}

div.sign_type_new table.tb_sign_type1 tbody tr td table.sign_member tbody tr td span
	{
	display: block;
	text-align: center;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	width: 70px;
	font-size: 12px;
	padding: 2px 4px
}

div.sign_type_new table.tb_sign_type1 tbody tr td table.sign_member tbody tr td.wrap_name,
	div.sign_type_new table.tb_sign_type1 tbody tr td table.sign_member tbody tr td.wrap_position,
	div.sign_type_new table.tb_sign_type1 tbody tr td table.sign_member tbody tr td.wrap_rank,
	div.sign_type_new table.tb_sign_type1 tbody tr td table.sign_member tbody tr td.wrap_type
	{
	height: 48px;
	text-align: center
}

div.sign_type_new table.tb_sign_type1 tbody tr td table.sign_member tbody tr td.wrap_sign
	{
	height: 74px;
	text-align: center;
}

div.sign_type_new table.tb_sign_type1 tbody tr td table.sign_member tbody tr td span.sign_stamp
	{
	height: 40px;
	padding-bottom: 0
}

div.sign_type_new table.tb_sign_type1 tbody tr td table.sign_member tbody tr td span.sign_stamp img
	{
	width: auto;
	height: 100%
}

div.sign_type_new table.tb_sign_type1 tbody tr td table.sign_member tbody tr td span.arbitrary
	{
	background: url(../images/arbitrary.gif) no-repeat;
	height: 36px;
	padding: 0
}

div.sign_type_new table.tb_sign_type1 tbody tr td table.sign_member tbody tr td.wrap_arbitrary
	{
	padding: 0
}

span.sign_type2 span.sign_member {
	border: 1px solid #000;
	padding: 4px;
	display: block;
	margin: 0 0 -1px
}

span.sign_type2 span.sign_member span.name {
	color: #333
}

span.sign_type2 span.sign_member span.date {
	margin: 0 !important
}

span.sign_type2 span.sign_member span.rank {
	margin: 0 0 0 4px
}

/* sign_type1_iline */
.sign_type1_inline {
	position: relative;
	display: inline-table !important;
	border-collapse: separate;
	border-spacing: 0;
	white-space: normal;
	font-size: 12px;
	margin: 1px -1px 0 0;
	padding-left: 26px;
}

.sign_type1_inline:after {
	display: block;
	clear: left;
	overflow: hidden;
	height: 0;
	content: ''
}

.sign_type1_inline:empty {
	padding-left: 0;
}

.sign_type1_inline .sign_tit_wrap {
	position: absolute;
	top: -1px;
	left: 0;
	bottom: 0;
	vertical-align: middle;
	border: 1px solid #000;
	width: 24px;
	background: #eee;
	display: table-cell
}

.sign_type1_inline .sign_tit_wrap .sign_tit {
	display: table;
	padding: 0 2px;
	text-align: center;
	word-break: break-all;
	white-space: normal;
	height: 100%
}

.sign_type1_inline .sign_tit_wrap .sign_tit strong {
	display: table-cell;
	vertical-align: middle
}

.sign_type1_inline .sign_member_wrap {
	vertical-align: top;
	background: #fff;
	display: table-cell;
	float: left;
	border: 1px solid #000;
	border-left: 0;
	margin: -1px 0 0 0
}

.sign_type1_inline .sign_member_wrap .sign_member {
	width: 79px;
	vertical-align: top;
	display: table;
	table-layout: fixed
}

.sign_type1_inline .sign_member_wrap .sign_member .sign_rank_wrap,
	.sign_type1_inline .sign_member_wrap .sign_member .sign_date_wrap {
	display: table-row
}

.sign_type1_inline .sign_member_wrap .sign_member .sign_rank,
	.sign_type1_inline .sign_member_wrap .sign_member .sign_wrap,
	.sign_type1_inline .sign_member_wrap .sign_member .sign_date {
	display: table-cell;
	vertical-align: middle;
	text-align: center;
	width: 79px
}

.sign_type1_inline .sign_member_wrap .sign_member .sign_rank {
	height: 26px
}

.sign_type1_inline .sign_member_wrap .sign_member .sign_wrap {
	height: 75px;
	border-top: 1px solid #000
}

.sign_type1_inline strong {
	font-weight: bold;
}

.sign_type1_inline .sign_member_wrap .sign_member .sign_wrap .arbitrary
	{
	background: url(../images/arbitrary.gif) no-repeat;
	height: 40px;
	width: 79px;
	padding: 0;
}

.sign_type1_inline .sign_member_wrap .sign_member .sign_date {
	height: 26px;
	border-top: 1px solid #000
}

.sign_type1_inline .sign_member_wrap .sign_member .sign_stamp {
	width: 79px;
	height: 40px
}

.sign_type1_inline .sign_member_wrap .sign_member .sign_stamp img {
	width: auto;
	height: 100%;
}

.sign_type1_inline .sign_member_wrap .sign_member .sign_wrap>span {
	padding: 2px 0;
	display: block
}

.sign_type1_inline .sign_rank, .sign_type1_inline .sign_name,
	.sign_type1_inline .sign_position, .sign_type1_inline .sign_type,
	.sign_type1_inline .sign_date {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	max-width: 79px;
}

.sign_arbitrary {
	background: url(../images/arbitrary.gif) no-repeat;
	height: 40px;
	width: 79px;
	padding: 0;
}

@charset
&quot;
UTF-8&quot;
;

/*login*/
.go_intro_wrap
header.go_header
{
background:
none
!important;
}
.go-gadget-content .slide_contents_warp .card_item:hover .btn_lead,
	.dashboard_box .card_item:hover .btn_lead, .login_box a.btn_login,
	.btn_function {
	background: #00a1b9 !important;
}

/*default*/
.login_box a.btn_login:hover {
	background-color: #008ea3;
	color: #fff;
}

.go_intro input.ipt_login, .go_intro div.change_pw input.ipt_login,
	.go_intro input.ipt_login, .go_intro .select_list {
	background-color: #fff !important;
	border-color: #ddd !important;
	-webkit-box-shadow: none;
	box-shadow: none;
}

.go_intro .login_id .select_list ul li {
	border-top-color: #fff;
}

.go_intro section.login_box {
	border-radius: 0;
}

.go_intro .login_id .select_list ul li:hover {
	background: #00a1b9;
}

.go_intro .login_id .select_list ul li a {
	font-weight: bold;
}

.go_intro section.login_box fieldset a.btn_login, .go_intro section.login_box fieldset a.btn_confirm
	{
	color: #fff;
}

.layer_confim_front .info {
	color: #00a1b9;
}

a.btn_bk {
	background: #00a1b9;
	border-color: #00a1b9;
	font-weight: bold;
}

a.btn_bk:hover {
	background-color: #008ea3;
	border-color: #008ea3;
	color: #fff;
}

.go_intro div.change_pw input.ipt_login:focus, .go_intro div.login_pw input.ipt_login:focus
	{
	border-color: #00a1b9;
}

.go_intro input.ipt_login::-webkit-input-placeholder {
	color: #00a1b9;
}

/* for chrome */
.go_intro input.ipt_login::-moz-placeholder {
	color: #00a1b9;
	opacity: 1;
}

/* for firefox 19+ */
.go_intro input.ipt_login:-moz-placeholder {
	color: #00a1b9;
}

/* for firefox 18- */
.go_intro input.ipt_login:-ms-input-placeholder {
	color: #00a1b9;
}

/* for IE */
/*go_header*/
/*GNB*/
/*classic*/
.go_skin_default.go_workspace_wide {
	background: none;
}

/*inverse_theme*/
.gadget_design_border .go-gadget-content ul.type_simple_list.today_list li:hover
	{
	background-color: #008ea3 !important;
}

/*medium dark*/
.go_wrap header.go_header nav ul li:hover {
	background-color: #00a1b9 !important;
}

/*medium dark*/
.go_wrap header.go_header_2row nav ul li.on {
	background-color: #00a1b9 !important;
}

.go_wrap header.go_header_2row .my_info ul.ctrl li:hover, .go_wrap header.go_header_2row .my_info ul.ctrl li.on
	{
	background-color: #008ea3 !important;
}

/*dark*/
.go_wrap header.go_header_2row nav ul li.on ul li {
	background-color: #fff !important;
}

.go_wrap header.go_header_2row nav ul li.on ul li span.menu {
	color: #333 !important;
}

.go_wrap header.go_header_2row .gnb_top_menu {
	border: none;
	background: #fff;
}

.go_wrap header.go_header.go_header_2row {
	border-color: #00a1b9;
	background: #fff;
	border-width: 2px;
	height: 42px;
}

/*custom*/
.go_wrap header.go_header nav ul li ul li:hover {
	background-color: #00a1b9;
}

/*default*/
.go_wrap header.go_header nav ul li:hover ul li.on_layer, .go_wrap header.go_header_2row .gnb_top_menu ul li,
	.go_wrap header.go_header_2row .gnb_top_menu ul li:hover, .go_wrap header.go_header_2row nav ul li.on ul li:hover
	{
	background-color: #fff !important;
}

/*deep dark*/
.go_wrap header.go_header_2row .gnb_top_menu ul li.bar, .go_wrap header.go_header section.my_info .profile span.photo,
	.go_wrap header.go_header_2row nav ul li.on ul, .go_wrap header.go_header_2row nav ul li.on ul li:first-child
	{
	border: none;
}

/*dark*/
.go_wrap header.go_header_2row nav ul li.on div.gnb_top_menu {
	margin: 0;
}

.go_wrap header.go_header_2row nav ul li ul li span.menu {
	color: #fff;
}

.go_wrap header.go_header_2row nav ul li ul li:hover span.menu, .go_wrap header.go_header_2row .gnb_top_menu a
	{
	color: #333 !important;
}

section.my_info ul.ctrl li:hover {
	background-color: #008ea3;
}

.go_wrap section.my_info ul.ctrl li a {
	background: url(../images/ic_snb.png?v3.0) no-repeat;
}

.go_wrap section.my_info .btn_docare .ic_docare {
	background: url(../images/ic_snb.png?v3.0) no-repeat 0 -100px !important;
}

/*mint-icon*/
.go_wrap section.my_info ul.ctrl li a.btn_noti {
	background: url(../images/ic_snb.png?v3.0) no-repeat -50px -100px
		!important;
}

/*mint-icon*/
.go_wrap section.my_info ul.ctrl li:hover {
	background: none;
}

.go_wrap header.go_header nav ul li a, .go_wrap section.my_info .profile span.info a.name
	{
	color: #fff;
}

/*font-color*/
.go_wrap header.go_header_2row nav ul li.more span.more {
	background: url(../images/ic_gnb_info.?v3.0) no-repeat -146px 15px;
}

.go_wrap header.go_header_advanced nav span.ctrl_workspace {
	border-color: #008ea3;
}

.go_wrap header.go_header_2row nav ul li span.menu {
	color: #333 !important;
}

.go_wrap section.my_info .btn_docare .txt {
	color: #00a1b9 !important;
}

.go_wrap header.go_header_2row nav ul li:hover span.menu, .go_wrap header.go_header_2row nav ul li.on span.menu
	{
	color: #fff !important;
}

.go_wrap header.go_header_2row nav ul li:hover .gnb_top_menu span.menu,
	.go_wrap header.go_header_2row nav ul li.on .gnb_top_menu .span.menu {
	color: #333 !important;
}

.go_wrap header.go_header_advanced nav span.ctrl_workspace {
	border: none;
}

.go_wrap header.go_header_2row nav ul li .ic_more {
	background: url(../images/ic_gnb.png?v3.0) no-repeat -250px -300px;
}

.go_wrap header.go_header_advanced nav span.ctrl_workspace span.wide {
	background: url(../images/ic_gnb_advanced.png?v3.0) no-repeat -50px
		-121px;
}

.go_wrap header.go_header_advanced nav span.ctrl_workspace span.normal {
	background: url(../images/ic_gnb_advanced.png?v3.0) no-repeat -100px
		-121px;
}

.go_wrap header.go_header_advanced nav span.ctrl_workspace:hover span.wide:hover,
	.go_wrap header.go_header_advanced nav span.ctrl_workspace:hover span.normal:hover
	{
	background-color: none;
}

.go_wrap header.go_header_advanced nav>span.ctrl_workspace:hover {
	background-color: none;
	background: none;
}

span.ic.ic_arrow_type1_down {
	background-position: -100px -1150px !important;
}

span.ic.ic_arrow_type1_up {
	background-position: -140px -1150px !important;
}

/*inverse_theme_end*/
/*go_side*/
ul.side_depth li p.on, section.lnb ul.side_depth li p.on:hover, nav.side_menu ul li.on,
	nav.side_menu ul li.on:hover {
	background: #ededed;
	border-color: #ededed;
}

/*side-list-active-background*/
ul.side_depth li p.on>a>span.txt, section.lnb ul.side_depth li p.on:hover>a>span.txt,
	nav.side_menu ul li.on a, nav.side_menu ul li.on:hover a {
	color: #333;
}

/*font-color*/
.gage {
	background: #99d9e3;
	border-color: #99d9e3;
}

/*common*/
::-moz-selection {
	background-color: #00a1b9;
}

::selection {
	background-color: #00a1b9;
}

/*mouse drag color : 중명도 혹은 고명도일 경우 텍스트와 명도대비확인*/
input[type= &quot ; text&quot; ]:hover, textarea:hover, select:hover,
	.go_renew input.txt:hover, .go_renew input[type=password]:hover,
	.go_renew textarea:hover, .go_renew select:hover, section.combine_search .c_search_wrap:hover,
	section.combine_search .c_search_wrap:focus {
	border-color: #00a1b9;
}

.go_renew .alert_box {
	background: #f4f4f4;
	border-color: #f9e5e4;
}

.btn_major_s, div.page_action_wrap .btn_major, footer.btn_layer_wrap .btn_major,
	span.btn_feedwrite, .go_todo .btn_major_s {
	background: #00a1b9 !important;
	border-color: #00a1b9 !important;
}

.btn_major_s:hover, div.page_action_wrap .btn_major:hover, footer.btn_layer_wrap .btn_major:hover,
	span.btn_feedwrite:hover, .go_todo .btn_major_s:hover {
	background-color: #008ea3 !important;
	border-color: #008ea3 !important;
	color: #fff;
}

ul.name_tag>li, table.type_normal tbody tr:hover, table.type_normal tbody tr.active,
	ul.article_list>li:hover, table.type_normal tbody tr:hover td, table.type_normal tbody tr.active td
	{
	background: #fafafa;
	border-color: none #99d9e3 !important;
}

ul.name_tag li.creat span.btn_wrap:hover span.txt {
	color: #00a1b9;
}

table td.on, .go-gadget-content div.layer_calendar table.tb_calendar_mini tbody tr td.on,
	.go-gadget-content div.layer_calendar table.tb_calendar_mini tbody tr td.on:hover
	{
	background: #ccecf1;
}

ul.briefing_list li.today span.ic_today {
	border-color: #00a1b9;
}

ul.gadget_list li:hover .wrap img {
	background: #f9e5e4;
}

ul.gadget_list li:hover .wrap div.on {
	background: #f9e5e4;
	border-color: #f9e5e4;
}

/*app_mail */
span.num, p.desc span.num, section.personal_data span.num strong, ul.tab_menu li:hover span.txt
	{
	color: #00a1b9 !important;
}

/*app_board &amp; app_community*/
a.plus_wrap span.plus {
	background: #00a1b9;
}

a.plus_wrap span.num, span.plus_num, section.classic_detail header.article_header h1 span.num,
	div.article_wrap div.info span.category, div.article_wrap div.info span.category a,
	span.list_subject:hover {
	color: #00a1b9;
}

td.size span.num {
	color: #999 !important;
}

/*default color*/
.meta_wrap div.plus_action a.btn_plus:hover, .meta_wrap div.plus_action a.on,
	.meta_wrap div.plus_action a.plus_on, .feed_contents_wrap div.plus_action a.btn_plus:hover,
	.feed_contents_wrap div.plus_action a.on, .feed_contents_wrap div.plus_action a.plus_on
	{
	background: #00a1b9;
	border-color: #008ea3;
}

div.plus_action a.btn_plus.on span.txt {
	color: #fff;
}

/*app_calendar*/
div.real_time {
	background: #00a1b9;
}

/*app_asset*/
section.asset_list div.article_wrap div.info span.count {
	color: #00a1b9;
}

/*app_report &amp; app_task*/
.card_item div.card_wrapper p.folder_type, ul.task_list span.category,
	ul.task_list>li div.meta_wrap span.date, .option_display span.byte,
	.option_task_additem table.in_table td.detail strong {
	color: #00a1b9;
}

.task_home ul.article_list>li:hover {
	background: #f4f4f4;
}

/*표준판 수정 필요*/
.tool_bar .critical .btn_major_s {
	background: transparent !important;
	border-color: transparent;
}

.tool_bar .critical .btn_major_s:hover {
	background: #eee !important;
	border-color: transparent;
	color: #333;
}

/*표준판 수정 필요 끝*/
/*app_todo+*/
.go_todo a.btn_menu {
	background: none;
}

div.row_wrap div.row_tit:hover .layer_transition .row_wrap .title:hover,
	.layer_transition .row_wrap ul li:hover {
	background: #00a1b9;
}

.go_todo a.btn_menu:hover {
	background: none;
}

.board_column_wrap header.board_column .num, .layer_type_detail>header .title h1+.txt
	{
	color: #00a1b9;
}

div.row_wrap div.row_tit:hover, .form_static_option .opt_public:hover,
	ul.list_default li a:hover {
	background: #00a1b9;
	color: #fff;
}

/* add */
section.my_info .wrap_docare:hover {
	background: none !important;
}

/* 3.0 */
.go_wrap header.go_header_2row nav ul li span.badge {
	background-color: #008ea3;
}

.go-dashboard .gadget_design_border .profile, .go-dashboard .gadget_design_border .today_list
	{
	background-color: #00a1b9;
}

.go-dashboard .gadget_design_border .today_list li .badge_zero {
	color: #fff !important;
}

.type_simple_list .badge {
	color: #999 !important;
}

section.function.function_attend a.btn_function {
	border-color: #00a1b9;
}

section.function.function_attend a.btn_function span.txt {
	color: #333 !important;
}

.btn_function .txt {
	color: #fff !important;
}

.type_btn_list_static li a {
	background: #00a1b9;
}

.type_btn_list_static li:hover a {
	background: #008ea3;
}

.layout_fixed table.gadget_login_info td.ip {
	color: #333;
}

.go-gadget-content .asset_list .btn_formal {
	border-color: #00a1b9;
}

.go-gadget-content .asset_list .btn_formal .txt {
	color: #333;
}

.go-dashboard .layer_calendar span.badge {
	background-color: #008ea3;
}

.go_skin_advanced .gadget_design_border .go-gadget-content ul.type_simple_list.today_list li:hover,
	.go_skin_advanced .go_wrap header.go_header nav ul li:hover {
	background-color: #00a1b9 !important;
}

.go_skin_advanced .go_wrap header.go_header nav ul li a:hover {
	background-color: #008ea3;
}

.go_skin_advanced .go_wrap header.go_header nav ul li.on a {
	background: #008ea3 !important;
}

.go_skin_advanced .go_wrap header.go_header_2row nav ul li.on,
	.go_skin_advanced .go_wrap header.go_header_2row nav ul li.on ul li,
	.go_skin_advanced .go_wrap header.go_header_2row .my_info ul.ctrl li:hover,
	.go_skin_advanced .go_wrap header.go_header_2row .my_info ul.ctrl li.on
	{
	background-color: #00a1b9 !important;
}

.go_skin_advanced .gadget_design_border .go-gadget-content,
	.go_skin_advanced .go_header {
	background: #00a1b9 !important;
	border-color: #00a1b9 !important;
}

.go_skin_advanced .go_header .btn_oganization {
	border-color: #fff;
}

.go_skin_advanced .go_wrap header.go_header_2row nav ul li span.menu,
	.go_skin_advanced .go_wrap section.my_info .btn_docare .txt,
	.go_skin_advanced .go_wrap header.go_header_2row nav ul li span.menu {
	color: #fff !important;
}

.go_skin_advanced .go_wrap section.my_info .btn_docare .ic_docare {
	background: url(../images/ic_snb.png?v3.0) no-repeat 0 -50px !important;
}

.go_skin_advanced .go_wrap section.my_info ul.ctrl li a.btn_noti {
	background: url(../images/ic_snb.png?v3.0) no-repeat -50px -50px
		!important;
}

.go-dashboard .gadget_design_wrap, .go-dashboard .go-gadget-content .tool_bar
	{
	background-color: #fff;
}

a.btn_mobile {
	background-color: #00a1b9;
}

a.btn_mobile span.ic_mobileVer {
	display: none;
}

div.layer_mailbox_detail section h1.on {
	background: #008ea3;
	border-color: #00a1b9;
}

section.function.function_attend .works_state .layer_transition {
	border-color: #00a1b9;
}

section.function.function_attend .layer_transition .row_wrap ul li:hover
	{
	background: #00a1b9;
}

.go_skin_advanced .go_wrap header.go_header.go_header_2row {
	height: auto !important;
}

/*표준판 수정 필요*/
.go-dashboard-editing .wrap_gadget_edit>.go_gadget_header {
	background: #333 !important;
}

.go-dashboard-editing .wrap_gadget_edit .gadget_edit footer.btn_layer_wrap a
	{
	border-left-color: #000;
}

.go-dashboard-editing .wrap_gadget_edit .gadget_edit footer.btn_layer_wrap a:hover
	{
	background: #008ea3 !important;
}

.go-dashboard-editing .wrap_gadget_edit {
	outline-color: #333 !important;
}

div.layer_notice>header {
	background: #00a1b9;
}

ul.side_depth li p.on>a>span.txt, ul.side_depth li p.on>a>span.contactTag,
	section.lnb ul.side_depth li p.on:hover>a>span.txt {
	color: #333 !important;
}

/*표준판 수정 필요 끝*/
/*# sourceMappingURL=go_color_deakyo.css.map */
/*user setting page massager hidden*/
div.go_side a.messenger_down {
	display: none
}

/* footer */
.footerAlign {
	left: 0;
	right: 0;
	bottom: 0;
	position: absolute;
	line-height: 8;
	overflow: hidden;
	margin-bottom: 16px;
}

.footerAlign .leftLogo {
	float: left;
	margin-left: 70px;
}

.footerAlign .rightLogo {
	float: right;
	margin-right: 70px;
}

.footerAlign .leftLogo img {
	vertical-align: bottom !important
}

/*siteAdmin popup*/
div.notice_type3 {
	left: 100px;
	width: 1300px;
}

div.notice_type3 iframe {
	height: 650px;
}

div.notice_type3 div.content div.wrap_notice {
	width: auto;
	overflow: auto;
	min-height: 200px;
	max-height: 800px;
	padding: 20px;
	margin-bottom: -3px
}

/*2022-05-11*/
.go_side .function .btn_function>.ic_side {
	display: none !important;
}

.go_skin_works .array_option ul.array_type li .ic_side+.txt {
	color: #333 !important;
}

.go_wrap header.go_header_2row nav ul li .ic_more {
	width: 16px;
	height: 24px;
	background: url(../images/ic_gnb.png?v3.0) no-repeat -230px -290px;
}
</style>
</head>

<body>
	<link
		href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css"
		rel="stylesheet">
	<script
		src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
	<span>
		<p
			style="line-height: 150%; font-family: 맑은 고딕; font-size: 10pt; margin-top: 0px; margin-bottom: 0px;">
			<span style="font-family: 맑은 고딕; font-size: 10pt;"></span>&nbsp;
		</p>
		<table
			style="border: 0px solid rgb(0, 0, 0); border-image: none; width: 800px; font-family: malgun gothic, dotum, arial, tahoma; margin-top: 1px; border-collapse: collapse;">
			<!-- Header -->
			<colgroup>
				<col width="310" />
				<col width="490" />
			</colgroup>

			<tbody>
				<tr>
					<td
						style="background: white; padding: 0px !important; border: 0px currentColor; border-image: none; height: 90px; text-align: center; color: black; font-size: 36px; font-weight: bold; vertical-align: middle;"
						colspan="2" class="">기안서</td>
				</tr>
				<tr>
					<td
						style="background: white; padding: 0px !important; border: currentColor; border-image: none; width: 506px; text-align: left; color: black; font-size: 12px; font-weight: normal; vertical-align: top;">
						<table
							style="border: 1px solid rgb(0, 0, 0); border-image: none; width: 356px; height: 130px; font-family: &amp; amp; amp; quot; malgun gothic&amp;amp; amp; quot; , dotum , arial, tahoma; margin-top: 1px; border-collapse: collapse;">
							<!-- User -->
							<colgroup>
								<col width="100" />
								<col width="210" />
							</colgroup>
							<tbody>
								<tr>
									<td
										style="background: rgb(221, 221, 221); padding: 5px; border: 1px solid black; border-image: none; height: 24px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bold; vertical-align: middle;">
										기안자</td>
									<td
										style="background: rgb(255, 255, 255); padding: 5px; border: 1px solid black; border-image: none; width: 272px; height: 24px; text-align: left; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;">
										${dto.memberDTO.name}</td>
								</tr>
								<tr>
									<td
										style="background: rgb(221, 221, 221); padding: 5px; border: 1px solid black; border-image: none; height: 24px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bold; vertical-align: middle;">
										직급</td>

									<td
										style="background: rgb(255, 255, 255); padding: 5px; border: 1px solid black; border-image: none; width: 272px; height: 24px; text-align: left; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;">
										${dto.memberDTO.position.name}</td>
								</tr>
								<tr>
									<td
										style="background: rgb(221, 221, 221); padding: 5px; border: 1px solid black; border-image: none; height: 24px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bold; vertical-align: middle;">
										기안일</td>
									<td
										style="background: rgb(255, 255, 255); padding: 5px; border: 1px solid black; border-image: none; width: 272px; height: 24px; text-align: left; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;">
										${dto.create_dt}</td>
								</tr>
							</tbody>
						</table>
					</td>
					<td id="modal-approval"
						style="background: white; padding: 0px !important; border: currentColor; border-image: none; width: 293px; text-align: right; color: black; font-size: 12px; font-weight: normal; vertical-align: top;">
						<p
							style="text-align: right; line-height: 150%; font-family: &amp; amp; amp; quot; malgun gothic&amp;amp; amp; quot; , dotum , arial, tahoma; font-size: 9pt; margin-top: 0px; margin-bottom: 0px;">
							<span id="approval-list" class="sign_type1_inline"
								style="font-family: &amp; amp; amp; quot; malgun gothic&amp;amp; amp; quot; , dotum , arial, tahoma; font-size: 9pt;"
								data-is-reception="" data-group-type="type1"
								data-group-max-count="7" data-group-name="결재선"
								data-group-seq="0"> <input type="hidden"
								id="approval-member-id" name="approval">
								<span class="sign_tit_wrap">
								<span class="sign_tit"><strong>결재선</strong></span>								
								</span>
								 
							<span class="sign_member_wrap" id=""> <span
									class="sign_member"> <span class="sign_rank_wrap">
											<span class="sign_rank" data-department-id="0"
											id="approvla-department-name0">${dto.approvalDTOs[0].memberDTO.department.name}</span>
									</span> <span class="sign_wrap"> <span class="sign_name"
											data-name-id="0" id="approval-name0">${dto.approvalDTOs[0].memberDTO.name}</span>
									</span> <span class="sign_date_wrap"> <span class="sign_date ">결재</span>
									</span>
								</span>
							</span> <span class="sign_member_wrap" id=""> <span
									class="sign_member"> <span class="sign_rank_wrap">
											<span class="sign_rank" data-department-id="1"
											id="approvla-department-name1">${dto.approvalDTOs[1].memberDTO.department.name}</span>
									</span> <span class="sign_wrap"> <span class="sign_name"
											data-name-id="1" id="approval-name1">${dto.approvalDTOs[1].memberDTO.name}</span>
									</span> <span class="sign_date_wrap"> <span class="sign_date ">결재</span>
									</span>
								</span>
							</span> <span class="sign_member_wrap" id=""> <span
									class="sign_member"> <span class="sign_rank_wrap">
											<span class="sign_rank" data-department-id="2"
											id="approvla-department-name2">${dto.approvalDTOs[2].memberDTO.department.name}</span>
									</span> <span class="sign_wrap"> <span class="sign_name"
											data-name-id="2" id="approval-name2">${dto.approvalDTOs[2].memberDTO.name}</span>
									</span> <span class="sign_date_wrap"> <span class="sign_date ">결재</span>
									</span>
								</span>
							</span>

							</span>
						</p>

					</td>
				</tr>
			</tbody>
		</table>
		<table
			style="border: 0px solid rgb(0, 0, 0); border-image: none; width: 800px; height: 89px; font-family: &amp; amp; amp; quot; malgun gothic&amp;amp; amp; quot; , dotum , arial, tahoma; margin-top: 10px; border-collapse: collapse;">
			<colgroup>
				<col width="100" />
				<col width="300" />
				<col width="100" />
				<col width="300" />
			</colgroup>
			<tbody>
				<tr>
					<td
						style="background: rgb(226, 226, 226); padding: 5px; border: 1px solid black; border-image: none; height: 10px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bolder; vertical-align: middle;">
						제목</td>
					<td
						style="background: rgb(255, 255, 255); padding: 5px; border: 1px solid black; border-image: none; height: 10px; text-align: left; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;"
						colspan="3">${dto.title}</td>
				</tr>
				<tr>
					<td
						style="background: rgb(226, 226, 226); padding: 5px; border: 1px solid black; border-image: none; height: 10px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bolder; vertical-align: middle;">
						참조자</td>
					<td id="budgetTeamInfo"
						style="background: rgb(255, 255, 255); padding: 5px; border: 1px solid black; border-image: none; height: 10px; text-align: left; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;">
						<c:forEach items="${dto.referrerDTOs}" var="r">
                                    ${r.memberDTO.name}
                                </c:forEach>
					</td>
					<td
						style="background: rgb(226, 226, 226); padding: 5px; border: 1px solid black; border-image: none; height: 10px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bolder; vertical-align: middle;">
						서류항목</td>
					<td id="quarterBudgetInfo"
						style="background: rgb(255, 255, 255); padding: 5px; border: 1px solid black; border-image: none; height: 10px; text-align: left; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;">
						${dto.file_randomname}
					</td>
				</tr>
				<tr>
					<td
						style="background: rgb(226, 226, 226); padding: 5px; border: 1px solid black; border-image: none; height: 21px; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bolder; vertical-align: middle;">
						첨부파일</td>
					<td id="productionCostInfo"
						style="background: rgb(255, 255, 255); padding: 5px; border: 1px solid black; border-image: none; height: 21px; text-align: left; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;"
						colspan="3"><c:forEach items="${dto.documentFileDTOs}"
							var="df">
							<a href="/resources/upload/documentFiles/${df.name}">${df.ori_name}</a>
						</c:forEach></td>
				</tr>
			</tbody>
		</table>
		<table
			style="border: 0px solid rgb(0, 0, 0); border-image: none; width: 800px; height: 206px; font-family: &amp; amp; amp; quot; malgun gothic&amp;amp; amp; quot; , dotum , arial, tahoma; margin-top: 1px; border-collapse: collapse;">
			<tbody>
				<tr>
					<td
						style="background: rgb(226, 226, 226); padding: 5px; border: 1px solid black; border-image: none; text-align: center; color: rgb(0, 0, 0); font-size: 12px; font-weight: bolder; vertical-align: middle;"
						colspan="2">내용</td>
				</tr>
				<tr>
					<td
						style="background: rgb(255, 255, 255); padding: 5px; border: 1px solid black; border-image: none; height: 178px; text-align: left; color: rgb(0, 0, 0); font-size: 12px; font-weight: normal; vertical-align: middle;">
						${dto.content}</td>
				</tr>
			</tbody>

		</table>
		<p
			style="line-height: 150%; font-family: &amp; amp; amp; quot; 맑은 고딕&amp;amp; amp; quot;; font-size: 10pt; margin-top: 0px; margin-bottom: 0px;">
			&nbsp;</p>
	</span>

	<script type="text/javascript">
		$("#content").summernote();
	</script>

</body>

</html>