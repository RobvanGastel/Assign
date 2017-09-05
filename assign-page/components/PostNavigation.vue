<template>
	<section class="brand-nav">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-md-10 col-md-offset-1">
					<nuxt-link :to="'/'">
						<img class="logo" src="/share-logo.png" alt="Assign logo">
						<h4 class="logo-text">Assign</h4>
					</nuxt-link>
					<nav>
						<ul>
							<li><nuxt-link class="btn-navcta" alt="Home" :to="'/'">Bekijk website</nuxt-link></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</section>
</template>
