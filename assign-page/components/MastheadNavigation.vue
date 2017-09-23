<template>
	<section class="brand-nav masthead-nav">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-lg-10 col-lg-offset-1">

					<nuxt-link :to="'/'" class="logo" title="Ga naar home">
						<img class="logo-image" alt="Assign logo" src="/logo.png">
						<h4 class="logo-text">Assign</h4>
					</nuxt-link>
					<div class="headway"></div>

					<nav>
						<ul>
							<li><nuxt-link class="lnk hidden-xs hidden-sm" title="Download" :to="'#appstore'">Download</nuxt-link></li>
							<li><nuxt-link class="lnk hidden-xs hidden-sm" title="Contact" :to="'/contact'">Contact</nuxt-link></li>
							<li><nuxt-link class="lnk hidden-xs hidden-sm" title="Vragen" :to="'/faq'">Vragen</nuxt-link></li>
							<li class="dropdown-toggle hidden-xs hidden-sm"><nuxt-link class="lnk dropdown-toggle" title="Functies" :to="'/#functies'">Functies</nuxt-link>
								<ul class="dropdown-nav">
									<li><nuxt-link class="lnk lnk-dropdown" title="Lees over Hashtags" :to="'/functies/hashtags'">Hashtags</nuxt-link></li>
									<li><nuxt-link class="lnk lnk-dropdown" title="Lees over Profielen" :to="'/functies/profiel'">Profielen</nuxt-link></li>
									<li><nuxt-link class="lnk lnk-dropdown" title="Lees over Notificaties" :to="'/functies/notificaties'">Notificaties</nuxt-link></li>
									<li><nuxt-link class="lnk lnk-dropdown" title="Lees over Communicatie manieren" :to="'/functies/communicatie'">Communicatie manieren</nuxt-link></li>
								</ul>
							</li>
							<li><nuxt-link class="btn-navcta" title="Vraag een demo aan" :to="'#demo'">Vraag demo aan</nuxt-link></li>
						</ul>
					</nav>

				</div>
			</div>
		</div>
	</section>
</template>
