<header class="navbar navbar-fixed-top navbar-shadow">
	<div class="navbar-branding">
		<b class="navbar-brand">Spring MVC App</b> <span
			id="toggle_sidemenu_l" class="ad ad-lines"></span>
	</div>
	<ul class="nav navbar-nav navbar-left">
		<li class="hidden-xs"><a class="request-fullscreen toggle-active"
			href="#"> <span class="ad ad-screen-full fs18"></span>
		</a></li>
	</ul>
	<div id="helpIconDiv" style="padding-top:12px; text-align:right; height:10px;">
		<img src="/resources/img/helpIcon.png" id="helpIcon" class="mw30 br64">
	</div>

	<ul class="nav navbar-nav navbar-right">
		<li class="menu-merge">
			<div style="padding-right:20px">
				<img src="/resources/img/avatars/6.jpg" alt="avatar" class="mw30 br64">
				<div class="dropdown">

					<span onclick="showHeaderDropdown()" class="dropbtn hidden-xs pl15" id="current-user"> ${user.username}</span>
					<div id="headerDropdown" class="dropdown-content">
						<a href=${user.username == 'nite' ? '/app/viewAccount' : (user.username == 'USER' ? '/user/viewAccount' : '#' )}>
							My Account
						</a>
						<a href="/logout">Logout</a>
					</div>

				</div>
			</div>
		</li>
	</ul>
</header>