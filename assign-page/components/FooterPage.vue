<template>
	<div class="row component-footerpage">

		<footer>
			<div class="container">
				<div class="row">

					<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 col-lg-offset-1 footer-section">
						<b>Hulp</b>
						<ul>
							<li><nuxt-link class="lnk" alt="Contact" :to="'/#contact'">Contact</nuxt-link></li>
							<li><a class="lnk" alt="Vragen" target="_blank" href="https://twitter.com/assignappnl">Vragen</a></li>
							<li><a class="lnk" alt="Vragen" href="mailto:assignapp.dev@gmail.com">Vragen</a></li>
							<li><nuxt-link class="lnk" alt="Vragen" :to="'/#faq'">Vragen</nuxt-link></li>
						</ul>
					</div>

					<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 footer-section">
						<b>Functies</b>
						<ul>
							<li><nuxt-link class="lnk" alt="Contact" :to="'/#contact'">Bellen</nuxt-link></li>
							<li><a class="lnk" alt="Vragen" target="_blank" href="https://twitter.com/assignappnl">Appen</a></li>
							<li><a class="lnk" alt="Vragen" href="mailto:assignapp.dev@gmail.com">SMS’n</a></li>
							<li><nuxt-link class="lnk" alt="Vragen" :to="'/#faq'">Mailen</nuxt-link></li>
						</ul>
					</div>

					<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2 footer-section">
						<b>Legal</b>
						<ul>
							<li><nuxt-link class="lnk" alt="Contact" :to="'/#contact'">Voorwaarden</nuxt-link></li>
							<li><a class="lnk" alt="Twitter" target="_blank" href="https://twitter.com/assignappnl">Privacy</a></li>
							<li><a class="lnk" alt="Vragen" href="mailto:assignapp.dev@gmail.com">Pers</a></li>
							<li><nuxt-link class="lnk" alt="Vragen" :to="'/#faq'">Release Notes</nuxt-link></li>
						</ul>
					</div>

				</div>
			</div>
		</footer>

	</div>
</template>
