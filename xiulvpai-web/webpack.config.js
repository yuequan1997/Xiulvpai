const path = require('path');
const webpack = require('webpack');
const webpackMerge = require('webpack-merge');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

const developmentConfig = require('./webpack.config.dev');
const productionConfig = require('./webpack.config.prod');

const srcdir = path.resolve(__dirname, 'src/main/assets');

const entries = {
    'javascripts/application': path.join(srcdir, 'javascripts/application.js'),
    'stylesheets/application': path.join(srcdir, 'stylesheets/application.scss'),
};

const commonConfig = {
    entry: entries,
    output: {
        publicPath: '/assets/'
    },
    module: {
        rules: [
            {
                test: /\.(sa|sc|c)ss$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    'css-loader',
                    'postcss-loader',
                    "sass-loader", // compiles Sass to CSS, using Node Sass by default
                ]
            },
            {
                test: '/\.js$/',
                exclude: /node_modules/,
                use: [
                    {
                        loader: 'babel-loader',
                        options: {presets: ['es2015', ['env', {module: false}]]}
                    }
                ]
            }
        ]
    },
    optimization: {
        namedModules: true,
        noEmitOnErrors: true,
        occurrenceOrder: true,
        // splitChunks: {
        //     cacheGroups: {
        //         default: false,
        //         vendors: false,
        //         common: {
        //             chunks: 'initial',
        //             name: 'javascripts/common',
        //             test: chunks => chunks.resource && !/^.*\.(css|scss)$/.test(chunks.resource) && /node_modules/.test(chunks.context)
        //         }
        //     }
        // }
    }
}

module.exports = (env) => env === 'dev'
    ? webpackMerge(commonConfig, developmentConfig)
    : webpackMerge(commonConfig, productionConfig);